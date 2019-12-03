package com.mayh.blog.service;

import com.mayh.blog.dao.CommentRepository;
import com.mayh.blog.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;
    private List<Comment> tempReplys = new ArrayList<>();

    @Transactional
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        List<Comment> comments = repository.findByBlogIdAndParentCommentNull(blogId, sort);
        return SortOutComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            comment.setParentComment(repository.findById(parentCommentId).get());
        } else {
            comment.setParentComment(null);
        }

        comment.setCreateTime(new Date());
        return repository.save(comment);
    }

    /**
     * 将子评论用列表保存
     *
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments
        ) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }
        combineChildren(commentsView);
        return commentsView;
    }

    private void combineChildren(List<Comment> comments) {
        for (Comment comment : comments
        ) {
            List<Comment> replys1 = comment.getReplyComments();
            for (Comment reply1 : comments
            ) {
                recursively(reply1);
            }
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
    }

    private void recursively(Comment comment) {
        tempReplys.add(comment);
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys
            ) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }

    /**
     * @param comments
     * @return
     */
    private List<Comment> SortOutComment(List<Comment> comments) {
        List returnComment = new ArrayList();
        for (Comment comment : comments
        ) {
            returnComment.add(comment);
            if (comment.getReplyComments() != null) {
                returnComment.addAll(SortOutComment(comment.getReplyComments()));
            }
        }
        return returnComment;
    }
}

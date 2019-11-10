package com.mayh.blog.service;

import com.mayh.blog.po.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listType(Pageable pageable);

    Tag updateTag(Long id, Tag tag);

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);

    void deleteTag(Long id);

    List<Tag> listTag();
}

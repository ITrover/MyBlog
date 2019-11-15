package com.mayh.blog.web.admin;

import com.mayh.blog.po.Tag;
import com.mayh.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.awt.*;

@Controller
@RequestMapping("/admin")
public class TagControler {
    public static final String INPUT = "admin/tags-input";
    public static final String LIST = "admin/tags";
    public static final String REDIRECT_LIST = "redirect:/admin/tags";
    @Autowired
    private TagService tagService;

    /**
     * 进入分类页面
     *
     * @param pageable * @param model
     * @return
     */
    @GetMapping("/tags")
    public String types(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                Pageable pageable, Model model) {
        model.addAttribute("page", tagService.listType(pageable));
        System.out.println(tagService.listTag());
        tagService.listType(pageable);
        return LIST;
    }

    /**
     * 添加新的标签
     *
     * @param model
     * @return
     */
    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return INPUT;
    }

    /**
     * 修改标签
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return INPUT;
    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        Tag type1 = tagService.getTagByName(tag.getName());
        if (type1 != null) {
            result.rejectValue("name", "nameError", "不能添加相同分类");
        }
        if (result.hasErrors()) {
            return INPUT;
        }
        Tag t = tagService.saveTag(tag);
        if (t == null) {
            //
            attributes.addFlashAttribute("message", "添加失败");
        } else {
            //
            attributes.addFlashAttribute("message", "添加成功");
        }
        return REDIRECT_LIST;
    }

    /**
     * 修改标签名称
     *
     * @param tag
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name", "nameError", "不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return INPUT;
        }
        Tag t = tagService.updateTag(id, tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }
}

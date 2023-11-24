package com.ruoyi.adopt.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.adopt.domain.Chat;
import com.ruoyi.adopt.service.IChatService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 聊天Controller
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/adopt/chat")
public class ChatController extends BaseController
{
    @Autowired
    private IChatService chatService;

    /**
     * 查询聊天列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:chat:list')")
    @GetMapping("/list")
    public TableDataInfo list(Chat chat)
    {
        startPage();
        List<Chat> list = chatService.selectChatList(chat);
        return getDataTable(list);
    }

    /**
     * 导出聊天列表
     */
    @PreAuthorize("@ss.hasPermi('adopt:chat:export')")
    @Log(title = "聊天", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Chat chat)
    {
        List<Chat> list = chatService.selectChatList(chat);
        ExcelUtil<Chat> util = new ExcelUtil<Chat>(Chat.class);
        util.exportExcel(response, list, "聊天数据");
    }

    /**
     * 获取聊天详细信息
     */
    @PreAuthorize("@ss.hasPermi('adopt:chat:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(chatService.selectChatById(id));
    }

    /**
     * 新增聊天
     */
    @PreAuthorize("@ss.hasPermi('adopt:chat:add')")
    @Log(title = "聊天", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Chat chat)
    {
        return toAjax(chatService.insertChat(chat));
    }

    /**
     * 修改聊天
     */
    @PreAuthorize("@ss.hasPermi('adopt:chat:edit')")
    @Log(title = "聊天", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Chat chat)
    {
        return toAjax(chatService.updateChat(chat));
    }

    /**
     * 删除聊天
     */
    @PreAuthorize("@ss.hasPermi('adopt:chat:remove')")
    @Log(title = "聊天", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(chatService.deleteChatByIds(ids));
    }
}

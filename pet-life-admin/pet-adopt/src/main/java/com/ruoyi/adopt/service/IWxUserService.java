package com.ruoyi.adopt.service;

import java.util.List;
import com.ruoyi.adopt.domain.WxUser;

/**
 * 小程序用户Service接口
 * 
 * @author ruoyi
 * @date 2022-02-22
 */
public interface IWxUserService 
{
    /**
     * 查询小程序用户
     * 
     * @param id 小程序用户主键
     * @return 小程序用户
     */
    public WxUser selectWxUserById(Long id);

    /**
     * 查询小程序用户列表
     * 
     * @param wxUser 小程序用户
     * @return 小程序用户集合
     */
    public List<WxUser> selectWxUserList(WxUser wxUser);

    /**
     * 新增小程序用户
     * 
     * @param wxUser 小程序用户
     * @return 结果
     */
    public int insertWxUser(WxUser wxUser);

    /**
     * 修改小程序用户
     * 
     * @param wxUser 小程序用户
     * @return 结果
     */
    public int updateWxUser(WxUser wxUser);

    /**
     * 批量删除小程序用户
     * 
     * @param ids 需要删除的小程序用户主键集合
     * @return 结果
     */
    public int deleteWxUserByIds(Long[] ids);

    /**
     * 删除小程序用户信息
     * 
     * @param id 小程序用户主键
     * @return 结果
     */
    public int deleteWxUserById(Long id);
}

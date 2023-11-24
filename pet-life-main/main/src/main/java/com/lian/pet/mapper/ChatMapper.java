package com.lian.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lian.pet.domain.entity.Chat;
import org.apache.ibatis.annotations.Select;
import java.util.List;


public interface ChatMapper extends BaseMapper<Chat> {

    /**
     * 查询聊天详情
     * @param userId1
     * @param userId2
     * @return
     */
    @Select("SELECT * FROM chat WHERE (from_id = #{userId1} and to_id = #{userId2})" +
            "or (to_id = #{userId1} and from_id = #{userId2})" +
            "ORDER BY create_time")
    List<Chat> queryChatsByUserId(String userId1, String userId2);

    /**
     * 查询对话列表
     * 这里聊天记录只展示99条
     * @param userId
     * @return
     */
    @Select("SELECT * FROM " +
            "(SELECT * FROM `chat` WHERE to_id = #{userId} or from_id = #{userId} " +
                "ORDER BY create_time DESC LIMIT 999) a " +
            "GROUP BY a.from_id,a.to_id " +
            "ORDER BY a.create_time DESC")
    List<Chat> queryChatsOrderBy(String userId);
}
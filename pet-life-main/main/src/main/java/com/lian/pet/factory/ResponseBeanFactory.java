package com.lian.pet.factory;

import com.alibaba.fastjson.JSONObject;
import com.lian.pet.common.basic.enums.ClientTypeEnum;
import com.lian.pet.common.basic.enums.UserStatusEnum;
import com.lian.pet.domain.dto.*;
import com.lian.pet.domain.entity.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @Desc: 数据体封装

 * @Time: 2022/1/13 15:00
 */
public class ResponseBeanFactory {

    /**
     * 小程序用户数据
     * @param openId
     * @param sessionKey
     * @param unionId
     * @param req
     * @return
     */
    public static WxUser getWxUser(String openId, String sessionKey, String unionId, WxUserDTO req) {
        WxUser wxUser = new WxUser();
        wxUser.setOpenId(openId);
        wxUser.setSessionKey(sessionKey);
        wxUser.setUnionId(unionId);
        wxUser.setAvatarUrl(req.getAvatarUrl());
        wxUser.setNickName(req.getNickname());
        wxUser.setCity(req.getCity());
        wxUser.setCountry(req.getCountry());
        wxUser.setProvince(req.getProvince());
        wxUser.setGender(req.getGender());
        wxUser.setPhone(req.getPhone());
        wxUser.setEmail(req.getEmail());
        wxUser.setUserType(ClientTypeEnum.WECHAT_MP.name());
        wxUser.setStatus(UserStatusEnum.NORMAL.getCode());
        return wxUser;
    }

    /**
     * 宠物领养数据
     * @param req
     * @return
     */
    public static PetAdopt getPetAdopt(AddPetAdoptDTO req, String urlPrefix) {
        PetAdopt petAdopt = new PetAdopt();
        petAdopt.setOpenId(req.getOpenId());
        petAdopt.setTitle(req.getTitle());
        petAdopt.setNickname(req.getNickname());
        petAdopt.setAge(req.getAge());
        petAdopt.setCity(req.getCity());
        petAdopt.setConditions(req.getConditions().toString()
                .replace("[", "").replace("]", ""));
        petAdopt.setContent(req.getContent());
        petAdopt.setPhone(req.getPhone());
        petAdopt.setPhysicalCondition(req.getPhysicalCondition().toString()
                .replace("[", "").replace("]", ""));
        petAdopt.setSex(req.getSex());
        petAdopt.setType(req.getType());
        petAdopt.setPictures(JSONObject.toJSONString(req.getPictures())
                .replace(urlPrefix, "")
                .replace("[", "").replace("]", ""));
        petAdopt.setPicPrefix(urlPrefix);
        petAdopt.setUpdateTime(new Date());
        return petAdopt;
    }

    /**
     * 寻宠数据
     * @param req
     * @return
     */
    public static PetFind getPetFind(AddPetFindDTO req, String urlPrefix) {
        PetFind petFind = new PetFind();
        petFind.setOpenId(req.getOpenId());
        petFind.setTitle(req.getTitle());
        petFind.setNickname(req.getNickname());
        petFind.setBread(req.getBreed());
        petFind.setAge(req.getAge());
        petFind.setCity(req.getCity());
        petFind.setAddress(req.getAddress());
        petFind.setContent(req.getContent());
        petFind.setPhone(req.getPhone());
        petFind.setSex(req.getSex());
        petFind.setType(req.getType());
        petFind.setPictures(JSONObject.toJSONString(req.getPictures())
                .replace(urlPrefix, "")
                .replace("[", "").replace("]", ""));
        petFind.setPicPrefix(urlPrefix);
        petFind.setUpdateTime(new Date());
        return petFind;
    }

    /**
     * 宠物动态数据
     * @param req
     * @return
     */
    public static PetCircle getPetCircle(AddPetCircleDTO req, String urlPrefix) {
        PetCircle petCircle = new PetCircle();
        petCircle.setUserId(req.getOpenId());
        petCircle.setContent(req.getContent());
        petCircle.setPictures(JSONObject.toJSONString(req.getPictures())
                .replace(urlPrefix, "")
                .replace("[", "").replace("]", ""));
        petCircle.setUpdateTime(new Date());
        return petCircle;
    }

    /**
     * 积分数据
     * @param openId
     * @param creditsNum
     * @param dayNum
     * @return
     */
    public static Credits getCredits(String openId, Integer creditsNum, Integer dayNum) {
        Credits credits = new Credits();
        credits.setOpenId(openId);
        credits.setCreditsNum(creditsNum);
        credits.setDayNum(dayNum);
        return credits;
    }

    /**
     * 评论数据
     * @param req
     * @return
     */
    public static Comment getComment(AddCommentDTO req) {
        Comment comment = new Comment();
        comment.setFromId(req.getFormId());
        comment.setGrandId(req.getGrandId());
        comment.setOwnerId(req.getOwnerId());
        comment.setParentId(req.getParentId());
        comment.setContent(req.getContent());
        comment.setType(req.getType());
        return comment;
    }

    /**
     * 聊天数据
     * @param req
     * @return
     */
    public static Chat getChat(AddChatDTO req) {
        Chat chat = new Chat();
        chat.setFromId(req.getFromId());
        chat.setToId(req.getToId());
        chat.setContent(req.getContent());
        return chat;
    }

    /**
     * 用户关注数据
     * @param req
     * @return
     */
    public static UserFollow getUserFollow(AddUserFollowDTO req) {
        UserFollow userFollow = new UserFollow();
        userFollow.setUserId(req.getUserId());
        userFollow.setFollowId(req.getFollowId());
        return userFollow;
    }

    public static Report getReport(AddReportDTO req, String urlPrefix) {
        Report report = new Report();
        report.setOpenId(req.getOpenId());
        report.setName(req.getName());
        report.setPhone(req.getPhone());
        report.setWechat(req.getWechat());
        report.setContent(req.getContent());
        report.setPictures(JSONObject.toJSONString(req.getPictures())
                .replace(urlPrefix, "")
                .replace("[", "").replace("]", ""));
        report.setPicPrefix(urlPrefix);
        report.setUpdateTime(new Date());
        return report;
    }

    public static Apply getApply(AddApplyDTO req) {
        Apply apply = new Apply();
        BeanUtils.copyProperties(req,apply);
        return apply;
    }

}

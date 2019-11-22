package com.bw.movie.model.bean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/11/20 16:30
 */
public class CinemaEvaluationBean {

    /**
     * result : [{"commentContent":"这接口是不是有问题啊！！！","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":125,"commentTime":1574211703000,"commentUserId":13821,"commentUserName":"老丁万岁","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"真好","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":123,"commentTime":1574162477000,"commentUserId":13766,"commentUserName":"nideyifa","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"很棒","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-08-27/20190827104532.png","commentId":106,"commentTime":1574135540000,"commentUserId":13541,"commentUserName":"大仙","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":" 好","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-08-15/20190815170633.png","commentId":103,"commentTime":1574135338000,"commentUserId":13485,"commentUserName":"秋冬","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"很棒","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-24/20191024160418.unknown","commentId":102,"commentTime":1574135145000,"commentUserId":13542,"commentUserName":"杨海鹏","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * commentContent : 这接口是不是有问题啊！！！
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
         * commentId : 125
         * commentTime : 1574211703000
         * commentUserId : 13821
         * commentUserName : 老丁万岁
         * greatHeadPic : []
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         */

        public String commentContent;
        public String commentHeadPic;
        public int commentId;
        public long commentTime;
        public int commentUserId;
        public String commentUserName;
        public int greatNum;
        public int hotComment;
        public int isGreat;
        public List<?> greatHeadPic;
    }
}

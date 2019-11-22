package com.bw.movie.model.bean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/23 14:50
 */
public class QueryCommentBean {

    /**
     * result : [{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1785,"commentTime":1571810199000,"commentUserId":13565,"commentUserName":"ggh","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4.5},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-21/20191021103725.png","commentId":1729,"commentTime":1571624968000,"commentUserId":13614,"commentUserName":"夏思博1","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":100},{"commentContent":"猪叫","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-21/20191021100949.jpg","commentId":1726,"commentTime":1571623889000,"commentUserId":13703,"commentUserName":"呜呜呜呜","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":90},{"commentContent":"\t电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-09-24/20190924153758.png","commentId":1725,"commentTime":1571620320000,"commentUserId":13617,"commentUserName":"牛杰","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4.5},{"commentContent":"电影好看1","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1723,"commentTime":1571453344000,"commentUserId":13445,"commentUserName":"����ʯ","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * commentContent : 电影好看
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
         * commentId : 1785
         * commentTime : 1571810199000
         * commentUserId : 13565
         * commentUserName : ggh
         * greatNum : 0
         * isGreat : 0
         * replyHeadPic : []
         * replyNum : 0
         * score : 4.5
         */

        public String commentContent;
        public String commentHeadPic;
        public int commentId;
        public long commentTime;
        public int commentUserId;
        public String commentUserName;
        public int greatNum;
        public int isGreat;
        public int replyNum;
        public double score;
        public List<?> replyHeadPic;
    }
}

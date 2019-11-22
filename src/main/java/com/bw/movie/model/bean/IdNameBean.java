package com.bw.movie.model.bean;

/**
 * 作者： 姓名
 * 日期： 2019/10/18 15:55
 */
public class IdNameBean {

    /**
     * result : {"email":"904858766@qq.com","headPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","id":13565,"lastLoginTime":1571385071000,"nickName":"ggh","sex":1}
     * message : 查询成功
     * status : 0000
     */

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        /**
         * email : 904858766@qq.com
         * headPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
         * id : 13565
         * lastLoginTime : 1571385071000
         * nickName : ggh
         * sex : 1
         */

        public String email;
        public String headPic;
        public int id;
        public long lastLoginTime;
        public String nickName;
        public int sex;
    }
}

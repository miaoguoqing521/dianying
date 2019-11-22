package com.bw.movie.model.bean;

/**
 * 作者： 姓名
 * 日期： 2019/9/29 14:26
 */
public class LoginBean {

    /**
     * result : {"sessionId":"156973834737913565","userId":13565,"userInfo":{"email":"904858766@qq.com","headPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","id":13565,"lastLoginTime":1569573437000,"nickName":"ggh","sex":1}}
     * message : 登陆成功
     * status : 0000
     */

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        /**
         * sessionId : 156973834737913565
         * userId : 13565
         * userInfo : {"email":"904858766@qq.com","headPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","id":13565,"lastLoginTime":1569573437000,"nickName":"ggh","sex":1}
         */

        public String sessionId;
        public int userId;
        public UserInfoBean userInfo;

        public static class UserInfoBean {
            /**
             * email : 904858766@qq.com
             * headPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
             * id : 13565
             * lastLoginTime : 1569573437000
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
}

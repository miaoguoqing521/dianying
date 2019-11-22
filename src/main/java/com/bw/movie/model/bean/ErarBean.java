package com.bw.movie.model.bean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/23 19:01
 */
public class ErarBean {

    /**
     * result : [{"id":1,"name":"青春光线电影院"},{"id":2,"name":"大观楼电影院"},{"id":3,"name":"首都电影院"},{"id":4,"name":"魔影国际影城"},{"id":6,"name":"北京CBD万达广场店"},{"id":8,"name":"北京博纳影城朝阳门旗舰店"},{"id":10,"name":"华谊兄弟影院"},{"id":12,"name":"美嘉欢乐影城中关村店"},{"id":14,"name":"中影国际影城北京小西天店"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * id : 1
         * name : 青春光线电影院
         */

        public int id;
        public String name;
    }
}

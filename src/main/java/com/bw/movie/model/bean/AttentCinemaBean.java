package com.bw.movie.model.bean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 16:23
 */
public class AttentCinemaBean {

    /**
     * result : [{"address":"滨河路乙1号雍和航星园74-76号楼","cinemaId":1,"logo":"http://172.17.8.100/images/movie/logo/qcgx.jpg","name":"青春光线电影院"},{"address":"中关村广场购物中心津乐汇三层（鼎好一期西侧）","cinemaId":12,"logo":"http://172.17.8.100/images/movie/logo/mjhlyc.jpg","name":"美嘉欢乐影城中关村店"},{"address":"前门大街大栅栏街36号","cinemaId":2,"logo":"http://172.17.8.100/images/movie/logo/dgl.jpg","name":"大观楼电影院"},{"address":"天桥南大街3号楼一层、二层（天桥艺术大厦南侧）","cinemaId":3,"logo":"http://172.17.8.100/images/movie/logo/sd.jpg","name":"首都电影院"},{"address":"远大路1号B座5层魔影国际影城","cinemaId":4,"logo":"http://172.17.8.100/images/movie/logo/mygj.jpg","name":"魔影国际影城"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * address : 滨河路乙1号雍和航星园74-76号楼
         * cinemaId : 1
         * logo : http://172.17.8.100/images/movie/logo/qcgx.jpg
         * name : 青春光线电影院
         */

        public String address;
        public int cinemaId;
        public String logo;
        public String name;
    }
}

package com.bw.movie.model.bean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/23 19:23
 */
public class PriceMovieQueryCinemaBean {

    /**
     * result : [{"address":"崇文门外大街18号国瑞城首层、地下二层","cinemaId":9,"logo":"http://172.17.8.100/images/movie/logo/blh.jpg","name":"北京百老汇影城国瑞购物中心店","price":0.13},{"address":"滨河路乙1号雍和航星园74-76号楼","cinemaId":1,"logo":"http://172.17.8.100/images/movie/logo/qcgx.jpg","name":"青春光线电影院","price":0.21}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * address : 崇文门外大街18号国瑞城首层、地下二层
         * cinemaId : 9
         * logo : http://172.17.8.100/images/movie/logo/blh.jpg
         * name : 北京百老汇影城国瑞购物中心店
         * price : 0.13
         */

        public String address;
        public int cinemaId;
        public String logo;
        public String name;
        public double price;
    }
}

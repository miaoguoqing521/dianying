package com.bw.movie.model.bean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/25 10:04
 */
public class FileSeatBean {

    /**
     * result : [{"row":"1","seat":"1","status":2},{"row":"1","seat":"2","status":1},{"row":"1","seat":"3","status":1},{"row":"1","seat":"4","status":2},{"row":"1","seat":"5","status":1},{"row":"1","seat":"6","status":1},{"row":"1","seat":"7","status":1},{"row":"1","seat":"8","status":1},{"row":"1","seat":"9","status":1},{"row":"2","seat":"1","status":1},{"row":"2","seat":"2","status":1},{"row":"2","seat":"3","status":1},{"row":"2","seat":"4","status":1},{"row":"2","seat":"5","status":1},{"row":"2","seat":"6","status":1},{"row":"2","seat":"7","status":1},{"row":"2","seat":"8","status":1},{"row":"2","seat":"9","status":1},{"row":"3","seat":"1","status":2},{"row":"3","seat":"2","status":1},{"row":"3","seat":"3","status":1},{"row":"3","seat":"4","status":1},{"row":"3","seat":"5","status":2},{"row":"3","seat":"6","status":1},{"row":"3","seat":"7","status":2},{"row":"3","seat":"8","status":2},{"row":"3","seat":"9","status":1},{"row":"4","seat":"1","status":1},{"row":"4","seat":"2","status":1},{"row":"4","seat":"3","status":1},{"row":"4","seat":"4","status":2},{"row":"4","seat":"5","status":2},{"row":"4","seat":"6","status":1},{"row":"4","seat":"7","status":1},{"row":"4","seat":"8","status":1},{"row":"4","seat":"9","status":1},{"row":"5","seat":"1","status":1},{"row":"5","seat":"2","status":1},{"row":"5","seat":"3","status":1},{"row":"5","seat":"4","status":1},{"row":"5","seat":"5","status":1},{"row":"5","seat":"6","status":1},{"row":"5","seat":"7","status":1},{"row":"5","seat":"8","status":1},{"row":"5","seat":"9","status":1},{"row":"6","seat":"1","status":1},{"row":"6","seat":"2","status":1},{"row":"6","seat":"3","status":1},{"row":"6","seat":"4","status":2},{"row":"6","seat":"5","status":1},{"row":"6","seat":"6","status":2},{"row":"6","seat":"7","status":2},{"row":"6","seat":"8","status":1},{"row":"6","seat":"9","status":1},{"row":"7","seat":"1","status":1},{"row":"7","seat":"2","status":1},{"row":"7","seat":"3","status":1},{"row":"7","seat":"4","status":1},{"row":"7","seat":"5","status":1},{"row":"7","seat":"6","status":1},{"row":"7","seat":"7","status":2},{"row":"7","seat":"8","status":1},{"row":"7","seat":"9","status":1},{"row":"8","seat":"1","status":1},{"row":"8","seat":"2","status":1},{"row":"8","seat":"3","status":1},{"row":"8","seat":"4","status":1},{"row":"8","seat":"5","status":1},{"row":"8","seat":"6","status":1},{"row":"8","seat":"7","status":2},{"row":"8","seat":"8","status":1},{"row":"8","seat":"9","status":2}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * row : 1
         * seat : 1
         * status : 2
         */

        public String row;
        public String seat;
        public int status;

        public String getRow() {
            return row;
        }

        public void setRow(String row) {
            this.row = row;
        }

        public String getSeat() {
            return seat;
        }

        public void setSeat(String seat) {
            this.seat = seat;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}

package com.ysp.ssm.demo;

import java.util.List;

/**
 * Created by yuhuanxi on 16/8/22.
 */
public class TipOff {

    private boolean success;
    private int code;
    private String message;
    private int curPage;
    private int pageCount;
    private int count;
    private boolean hasRecords;

    private List<ResultsBean> results;

    public TipOff() {
    }

    @Override
    public String toString() {
        return "TipOff{" +
                ", success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", curPage=" + curPage +
                ", pageCount=" + pageCount +
                ", count=" + count +
                ", hasRecords=" + hasRecords +
                ", results=" + results +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isHasRecords() {
        return hasRecords;
    }

    public void setHasRecords(boolean hasRecords) {
        this.hasRecords = hasRecords;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private int uid;
        private int type;
        private long createdTs;
        private Object plan;
        private Object stage;

        private CommentBean comment;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getCreatedTs() {
            return createdTs;
        }

        public void setCreatedTs(long createdTs) {
            this.createdTs = createdTs;
        }

        public Object getPlan() {
            return plan;
        }

        public void setPlan(Object plan) {
            this.plan = plan;
        }

        public Object getStage() {
            return stage;
        }

        public void setStage(Object stage) {
            this.stage = stage;
        }

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public static class CommentBean {
            private int uid;
            private int commentId;
            private String content;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getCommentId() {
                return commentId;
            }

            public void setCommentId(int commentId) {
                this.commentId = commentId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        @Override
        public String toString() {
            return "ResultsBean{" +
                    "uid=" + uid +
                    ", type=" + type +
                    ", createdTs=" + createdTs +
                    ", plan=" + plan +
                    ", stage=" + stage +
                    ", comment=" + comment +
                    '}';
        }
    }
}

package com.common.entity.eparkingCloud;

public class TParkInOutCriteriaDIY extends TBlacklistCriteria{

    public static class Criteria  {

        protected Criteria() {
            super();
        }

        public Criteria orStatusSrtStatus(int status) {
            //addCriterion("status<" + status + " or (status=3 and srt_status in(0,2))");
            return (Criteria) this;
        }
    }


}

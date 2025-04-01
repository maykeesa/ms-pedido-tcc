package br.com.ms.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ResponseDto {

    public enum Body{;

        @Data
        @AllArgsConstructor
        public static class Response{
            private int status;
            private Object body;
        }

        @Data
        public static class ResponseError{
            private int status;
            private Object error;
            private Object cause;

            public ResponseError(int status, Object error, Object cause) {
                this.status = status;
                this.error = error;
                this.cause = cause;
            }
        }
    }
}

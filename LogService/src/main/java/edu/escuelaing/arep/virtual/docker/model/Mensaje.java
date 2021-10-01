package edu.escuelaing.arep.virtual.docker.model;

public class Mensaje {
        private String mensaje;

        /**
         * Constructor del mensaje
         * @param mensaje el mensaje
         */
        public Mensaje(String mensaje) {
            this.mensaje=mensaje;
        }

        /**
         * get del mensaje
         * @return el mensaje
         */
        public String getMensaje() {
            return mensaje;
        }

        /**
         * set del mensaje
         * @param mensaje el mensaje
         */
        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
}

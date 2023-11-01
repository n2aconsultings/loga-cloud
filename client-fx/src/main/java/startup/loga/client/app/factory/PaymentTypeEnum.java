package startup.loga.client.app.factory;

public enum PaymentTypeEnum {
    ESPECES {
        @Override
        public String getPaymentType() {
            return "ESPECES";
        }
    },
    VIREMENT {
        @Override
        public String getPaymentType() {
            return "VIREMENT";
        }
    },
    CARTEBANCAIRE {
        @Override
        public String getPaymentType() {
            return "CARTEBANCAIRE";
        }
    },
    MOBILEMONEY {
        @Override
        public String getPaymentType() {
            return "MOBILEMONEY";
        }
    },
    CHEQUES {
        @Override
        public String getPaymentType() {
            return "CHEQUES";
        }
    },
    CREDIT {
        @Override
        public String getPaymentType() {
            return "CREDIT";
        }
    },
    AUTRE {
        @Override
        public String getPaymentType() {
            return "AUTRE";
        }
    };

    public abstract String getPaymentType();
}

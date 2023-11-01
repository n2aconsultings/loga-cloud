package startup.loga.client.app.factory;

public enum InvoiceTypeEnum {
        FV {
                @Override
                public String getInvoiceType() {
                        return "FV";
                }
        },EV {
                @Override
                public String getInvoiceType() {
                        return "EV";
                }
        },FA {
                @Override
                public String getInvoiceType() {
                        return "FA";
                }
        },EA {
                @Override
                public String getInvoiceType() {
                        return "EA";
                }
        };

        public abstract String getInvoiceType();
}

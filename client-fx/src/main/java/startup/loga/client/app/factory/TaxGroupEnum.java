package startup.loga.client.app.factory;

public enum TaxGroupEnum {
    A {
        @Override
        public String getTaxGroup() {
            return "A";
        }
    }, B {
        @Override
        public String getTaxGroup() {
            return "B";
        }
    }, C {
        @Override
        public String getTaxGroup() {
            return "C";
        }
    }, D {
        @Override
        public String getTaxGroup() {
            return "D";
        }
    }, E {
        @Override
        public String getTaxGroup() {
            return "E";
        }
    }, F {
        @Override
        public String getTaxGroup() {
            return "F";
        }
    };

    public abstract String getTaxGroup();
}

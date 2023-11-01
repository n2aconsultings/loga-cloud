package startup.loga.client.app.factory;

public enum AibGroupTypeEnum {
    A {
        @Override
        public String getAib() {
            return "A";
        }
    },B {
        @Override
        public String getAib() {
            return "B";
        }
    };

    public abstract String getAib();
}

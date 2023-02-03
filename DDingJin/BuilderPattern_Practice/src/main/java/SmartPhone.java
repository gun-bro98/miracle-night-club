public class SmartPhone {
    private int storage;
    private int RAM;

    private boolean isGpuEnabled;
    private boolean isWifi6Enabled;

    private SmartPhone(SmartPhoneBuilder builder) {
        this.storage = builder.storage;
        this.RAM = builder.RAM;
        this.isGpuEnabled = builder.isGpuEnabled;
        this.isWifi6Enabled = builder.isWifi6Enabled;
    }

    public static class SmartPhoneBuilder {
        private int storage;
        private int RAM;
        private boolean isGpuEnabled;
        private boolean isWifi6Enabled;

        public int getYourStorage() {
            return this.storage;
        }

        public SmartPhoneBuilder(int storage, int RAM) {
            this.storage = storage;
            this.RAM = RAM;
        }

        public SmartPhoneBuilder setGpuEnabled(boolean set) {
            this.isGpuEnabled = set;
            return this;
        }

        public SmartPhoneBuilder setWifi6Enabled(boolean set) {
            this.isWifi6Enabled = set;
            return this;
        }

        public SmartPhone build() {
            SmartPhone smartPhone = new SmartPhone(this);
            return smartPhone;
        }

    }
}

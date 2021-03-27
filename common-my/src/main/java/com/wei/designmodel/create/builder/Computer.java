package com.wei.designmodel.create.builder;

/**
 * @author wei.peng
 * @descripe 建造者模式
 * @Date 2019/8/26 0026.
 */
public class Computer {
    private String cpu;
    private String mainboard;
    private String ram;

    public Computer() {}

    public Computer(String cpu, String mainboard, String ram) {
        this.cpu = cpu;
        this.mainboard = mainboard;
        this.ram = ram;
    }

    public static class Builder{
        private String cpu;
        private String mainboard;
        private String ram;

        public static Builder builder(){
            return new Builder();
        }

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setMainboard(String mainboard) {
            this.mainboard = mainboard;
            return this;
        }

        public Builder setRam(String ram) {
            this.ram = ram;
            return this;
        }

        public Computer create(){
            return new Computer(cpu, mainboard, ram);
        }
    }

    @Override
    public String toString() {
        return String.format("cpu = %s, mainboard = %s, ram = %s", this.cpu, this.mainboard, this.ram);
    }
}

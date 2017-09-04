package com.iflytek.sunflower;

public class FlowerCollector {

    public enum Gender {
        Male,
        Female,
        Unknown;

        public static Gender[] a() {
            return (Gender[]) a.clone();
        }
    }
}

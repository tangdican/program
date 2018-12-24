public enum Elvis {
    INSTANCE;

    public void leaveTheBuilding(Object o) {
        System.out.println("Whoa baby, I'm outta here!"+o.hashCode());
    }

    // This code would normally appear outside the class!
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding(elvis);

        Elvis elvis2 = Elvis.INSTANCE;
        elvis2.leaveTheBuilding(elvis2);
    }
}
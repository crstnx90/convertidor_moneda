public record Moneda(String base_code,String target_code,double conversion_rate,double conversion_result, double monto) {

    @Override
    public String toString() {
        return monto+" "+base_code+" , a "+ target_code+" son: "+conversion_result+" "+target_code;
    }
}

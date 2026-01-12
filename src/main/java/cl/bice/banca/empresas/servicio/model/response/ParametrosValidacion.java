package cl.bice.banca.empresas.servicio.model.response;

public class ParametrosValidacion {
    
    String  valorParametro;
    
    String  valorParametro2;
    
    String  valorParametro3;
    
    String  valorParametro4;

    /**
     * @return the valorParametro
     */
    public String getValorParametro() {
        return valorParametro;
    }

    /**
     * @param valorParametro the valorParametro to set
     */
    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }

    /**
     * @return the valorParametro2
     */
    public String getValorParametro2() {
        return valorParametro2;
    }

    /**
     * @param valorParametro2 the valorParametro2 to set
     */
    public void setValorParametro2(String valorParametro2) {
        this.valorParametro2 = valorParametro2;
    }

    /**
     * @return the valorParametro3
     */
    public String getValorParametro3() {
        return valorParametro3;
    }

    /**
     * @param valorParametro3 the valorParametro3 to set
     */
    public void setValorParametro3(String valorParametro3) {
        this.valorParametro3 = valorParametro3;
    }

    /**
     * @return the valorParametro4
     */
    public String getValorParametro4() {
        return valorParametro4;
    }

    /**
     * @param valorParametro4 the valorParametro4 to set
     */
    public void setValorParametro4(String valorParametro4) {
        this.valorParametro4 = valorParametro4;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ParametrosValidacion [valorParametro=");
        builder.append(valorParametro);
        builder.append(", valorParametro2=");
        builder.append(valorParametro2);
        builder.append(", valorParametro3=");
        builder.append(valorParametro3);
        builder.append(", valorParametro4=");
        builder.append(valorParametro4);
        builder.append("]");
        return builder.toString();
    }
    
}

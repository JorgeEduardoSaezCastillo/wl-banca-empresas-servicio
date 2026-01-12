package cl.bice.banca.empresas.servicio.model.common;

public enum TipoMoneda {

		CLP("000"),
	    USD("013"),
	    EUR("142"),
		UF("300");
	
	    private String codigo;
	    
	    TipoMoneda(String codigo) {
	        this.codigo = codigo;
	    }

	    public String value() {
	        return name();
	    }

	    public static TipoMoneda fromValue(String v) {
	        return valueOf(v);
	    }

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
	    
}

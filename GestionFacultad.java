
public abstract class GestionFacultad {

    private String id;
    private String nombre;
    private String apellido;
    private float sueldo;

    public static final int SALARIO_BASICO = 386;

    public abstract void generaSueldo();

    public GestionFacultad(String id, String nombre, String apellido, float sueldo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sueldo = sueldo;
    }

    public GestionFacultad() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }
}

abstract class Empleado extends GestionFacultad {

    private int anioincorporacion;
    private int numDespacho;

    public Empleado(int anioincorporacion, int numDespacho, String id, String nombre, String apellido, float sueldo) {
        super(id, nombre, apellido,sueldo);
        this.anioincorporacion = anioincorporacion;
        this.numDespacho = numDespacho;
    }

    public Empleado() {
    }

    public int getAnioincorporacion() {
        return anioincorporacion;
    }

    public void setAnioincorporacion(int anioincorporacion) {
        this.anioincorporacion = anioincorporacion;
    }

    public int getNumDespacho() {
        return numDespacho;
    }

    public void setNumDespacho(int numDespacho) {
        this.numDespacho = numDespacho;
    }

    public void cambioDespacho(int nuevoDes) {
        setNumDespacho(nuevoDes);
        System.out.println("Despacho cambiado correctamente");
    }
    
    public void generaSueldo(int AnioincorporacionUno) {
        float salario;
        if (AnioincorporacionUno < 1999 && AnioincorporacionUno > 1980) {
            salario = SALARIO_BASICO + (SALARIO_BASICO * 0.4f);
            setSueldo(salario);
        } else if (AnioincorporacionUno < 2008 && AnioincorporacionUno > 1998) {
            salario = SALARIO_BASICO + (SALARIO_BASICO * 0.25f);
            setSueldo(salario);
        } else {
            salario = SALARIO_BASICO + (SALARIO_BASICO * 0.15f);
            setSueldo(salario);
        }
    }
    @Override
    public String toString() {
        return String.format("Trabajador%s\nAños de experiencia:%d\nSalario: %.2f\nCubículo: %d",getNombre(),(2018-1980),getSueldo(),getNumDespacho());
    }
}

abstract class Estudiante extends GestionFacultad {

    private int ciclo;

    public Estudiante() {
    }

    public Estudiante(int ciclo, String id, String nombre, String apellido,float sueldo) {
        super(id, nombre, apellido,sueldo);
        this.ciclo = ciclo;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public void matricular(int ciclo) {
        setCiclo(ciclo);
    }

    public float generaSueldo(int op) {
        int sueldo;
        switch (op) {
            case 1:
                sueldo = SALARIO_BASICO;
                break;
            default:
                sueldo = 0;
                break;
        }
        return sueldo;
    }

    public String toString(int op) {
        if (op == 1) {
            return String.format("El estudiante %s\nSe ha matriculado en %d°ciclo\nRecibe un salario por concepto de beca de %.2f$", getNombre(), getCiclo(), generaSueldo(1));
        } else {
            return String.format("El estudiante %s\nSe ha matriculado en %d°ciclo\nRecibe un salario por concepto de beca de %.2f$", getNombre(), getCiclo(), generaSueldo(op));
        }
    }
}

abstract class Profesor extends GestionFacultad {

    private String tipo;
    private String departamento;

    public Profesor() {
    }

    public Profesor(String tipo, String departamento, String id, String nombre, String apellido,float sueldo) {
        super(id, nombre, apellido,sueldo);
        this.tipo = tipo;
        this.departamento = departamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void asignArea(int op) {
        switch (op) {
            case 1:
                setDepartamento("Area Técnica");
                break;
            case 2:
                setDepartamento("Area SocioHumanística");
                break;
            case 3:
                setDepartamento("Area Biológica");
                break;
            case 4:
                setDepartamento("Area Idiomas");
                break;
        }
    }

    public float generaSueldo(int tipo) {
        float sueldo;
        if (tipo == 1) {
            sueldo = SALARIO_BASICO * 6 + (SALARIO_BASICO * 0.15f);
        } else {
            sueldo = SALARIO_BASICO * 3 + (SALARIO_BASICO * 0.15f);
        }
        return sueldo;
    }
    
    public String toString(int tp){
        if(tp==1)
        return String.format("El docente %s\nPerteneciente al %s\nTipo: Tiempo completo\nPosee un salario mensual de %.2f",getNombre(),getDepartamento(),generaSueldo(1));
        else
        return String.format("El docente %s\nPerteneciente al %s\nTipo: Tiempo parcial\nPosee un salario mensual de %.2f",getNombre(),getDepartamento(),generaSueldo(2));
    }
}
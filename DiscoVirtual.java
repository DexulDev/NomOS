/**
 *
 * @author Club Penguin
 * @version 0.1
 */
public class DiscoVirtual
{
    public static final int NumBloques = 32;
    public static final int TamBloque = 16;

    private Bloque[] bloques;

    public DiscoVirtual(){
        bloques = new Bloque[NumBloques];
        for(int i = 0;i<NumBloques; i++){
            bloques[i] = new Bloque(i);
        }
    }

    public int buscarLibre(){
        for(int i = 0;i<NumBloques; i++){
            if(!bloques[i].siOcupado()) return i;
        }
        return -1;
    }

    public int bloquesLibres(){
        int libres = 0;
        for(int i = 0;i<NumBloques; i++){
            if(!bloques[i].siOcupado()) libres++;
        }
        return libres;
    }

    public Bloque getBloque(int id){
        return bloques[id];
    }

    public String mapa(){
        String mapa = "Mapa del disco ([x] ocupado, [ ] libre)\n";
        for(int i = 0;i<NumBloques; i++){
            if(i%8 == 0){
                String inicio = (i<10 ? "0" : "") + i;
                String fin = (i+7<10 ? "0" : "") + (i+7);
                mapa += inicio + "-" + fin + "  ";
            }
            mapa += bloques[i].siOcupado() ? "[x]" : "[ ]";
            if(i%8 == 7) mapa += "\n";
        }
        return mapa + "Libres: " + bloquesLibres() + "/" + NumBloques + " bloques";
    }
}

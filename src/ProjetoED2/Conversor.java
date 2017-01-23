package ProjetoED2;
import java.nio.ByteBuffer;


public class Conversor {
    public static ByteBuffer getBuffer(Aluno a){
        ByteBuffer buf = ByteBuffer.allocate(Aluno.TAM);

        // posicoes
        // 0....8...68....148...198
        // -----8 + 60 bla bla e bla

        buf.putLong(a.getMatric());
        buf.put((a.getNome()).getBytes());
        buf.position(68);
        buf.put((a.getEndereco()).getBytes());
        buf.position(148);
        buf.put((a.getEmail()).getBytes());
        buf.position(198);
        buf.putShort(a.getCurso());
        buf.flip();

        return buf;
    }

    public static Aluno getAluno(ByteBuffer buf) {
        Aluno al = new Aluno();
        buf.position(0);
        al.setMatric(buf.getLong());

        byte[] vNome = new byte[60];
        buf.get(vNome);
        String nome = new String(vNome);
        nome = nome.trim();   // retira os espacos vazios
        al.setNome(nome);

        byte[] vEnd = new byte[80];
        buf.get(vEnd);
        String end = new String(vEnd);
        end = end.trim();
        al.setEndereco(end);

        byte[] vEmail = new byte[50];
        buf.get(vEmail);
        String email = new String(vEmail);
        email = email.trim();
        al.setEmail(email);

        al.setCurso(buf.getShort());
        
        return al;
    }
    
}
package ProjetoED2;
import java.nio.ByteBuffer;


public class Aluno implements IFileOrganizer{
	private long matric; //8 Bytes
	private String nome; //60 Bytes
	private String endereco; //80 Bytes
	private String email; //50 Bytes
	private short curso; //2 Bytes
	
	public static final int TAM = 200;
	
	public Aluno(){}
	
	public Aluno(long matric, String nome, String endereco, String email, short curso){
		this.matric = matric;
		this.endereco = endereco;
		this.nome = nome;
		this.email = email;
		this.curso = curso;	
	}
	
	public long getMatric() {
		return matric;
	}

	public void setMatric(long matric) {
		this.matric = matric;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public short getCurso() {
		return curso;
	}

	public void setCurso(short curso) {
		this.curso = curso;
	}
	
	public static Aluno getAluno(ByteBuffer buffer){
		Aluno a = new Aluno();
		
		a.setMatric(buffer.getLong());
		
		byte[] vNome = new byte[60];
		buffer.get(vNome);
		String nome = new String(vNome);
		nome = nome.trim();
		a.setNome(nome);
		
		byte[] vEndereco = new byte[80];
		buffer.get(vEndereco);
		String endereco = new String(vEndereco);
		endereco = endereco.trim();
		a.setEndereco(endereco);
		
		byte[] vEmail = new byte[50];
		buffer.get(vEmail);
		String email = new String(vEmail);
		email = email.trim();
		a.setEmail(email);
		
		a.setCurso(buffer.getShort());
		
		return a;
	}

	public static void main(String[] args) {
		//Aluno a = new Aluno(241169,"Ismael","Rua 22","foo@mail.com",(short)4);
		//xByteBuffer buffer = Conversor.getBuffer(a);
	}

	@Override
	public void addAluno(Aluno a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Aluno getAluno(long matric) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aluno delAluno(long matric) {
		// TODO Auto-generated method stub
		return null;
	}
}



package ProjetoED2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;

public class OrganizadorSimples implements IFileOrganizer{
	
	private FileChannel canal;
	
	public OrganizadorSimples(String fileName) throws FileNotFoundException{
		File file = new File(fileName);
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		this.canal = raf.getChannel();
	}
	
	@Override
	public void addAluno(Aluno a) {
		try {
			ByteBuffer buf = Conversor.getBuffer(a);
			long pos = this.canal.size();
			this.canal.write(buf, pos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Aluno getAluno(long matric) {
		try {
			this.canal.position(0);
			long size = this.canal.size();
			while( canal.position() < size){
				ByteBuffer buf = ByteBuffer.allocate(Aluno.TAM);
				this.canal.read(buf);
				if(matric == buf.getLong(0)){
					buf.position(0);
					return Conversor.getAluno(buf);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Aluno delAluno(long matric) {
		try {
			this.canal.position(0);
			long size = this.canal.size();
			while(this.canal.position()<size){
				ByteBuffer buf = ByteBuffer.allocate(Aluno.TAM);
				this.canal.read(buf);
				if (matric == buf.getLong(0)){
					long pos = this.canal.position() - Aluno.TAM;
					ByteBuffer bufLost = ByteBuffer.allocate(Aluno.TAM);
					this.canal.read(bufLost, this.canal.size() - Aluno.TAM);
					bufLost.flip();
					this.canal.write(bufLost, pos);
					this.canal.truncate(this.canal.size() -  Aluno.TAM);
					buf.position(0);
					return Conversor.getAluno(buf);
				}
			}
			canal.truncate(Aluno.TAM - 200);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	

}

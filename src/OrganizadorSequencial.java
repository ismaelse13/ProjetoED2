
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;


class OrganizadorSequencial implements IFileOrganizer {
	
	private FileChannel canal;
	
	public OrganizadorSequencial(String fileName) throws FileNotFoundException{
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
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getPosition(long matric){
		int i = 0;
		try {
			this.canal.position(0);
			long size = this.canal.size();
			while( canal.position() < size){
				i++;
				ByteBuffer buf = ByteBuffer.allocate(Aluno.TAM);
				this.canal.read(buf);
				if(matric == buf.getLong(0)){
					buf.position(0);
					return i;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * Retorna o item no banco de dados na posicao passada, como uma array e tals.
	 * 
	 * @param index 	Posição do item desejado
	 * @return 			Retorna o objeto do aluno buscado
	 */
	public Aluno getItemIndex(int index){
		try {
			long posicaoBytes = (index-1) * Aluno.TAM;
			this.canal.position(posicaoBytes);
			ByteBuffer buf = ByteBuffer.allocate(Aluno.TAM);
			this.canal.read(buf);
			return Conversor.getAluno(buf);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Aluno getIndexAluno(int index){

    }

    public void realocaAluno(int alunoFonte, int alunoDestino){

    }


    
}
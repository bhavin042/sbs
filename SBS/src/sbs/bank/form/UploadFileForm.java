package sbs.bank.form;
 
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
 
public class UploadFileForm {
 
    private List<MultipartFile> crunchifyFiles;
    private MultipartFile mfile;
    private String uName;
 
    public MultipartFile getMfile() {
		return mfile;
	}

	public String getUname() {
		return uName;
	}

	public void setuName(String ruName) {
		this.uName = ruName;
	}

	public void setMfile(MultipartFile mfile) {
		this.mfile = mfile;
	}

	public List<MultipartFile> getFiles() {
        return crunchifyFiles;
    }
 
    public void setFiles(List<MultipartFile> files) {
        this.crunchifyFiles = files;
    }
}
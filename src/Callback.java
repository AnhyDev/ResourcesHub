package okhttp3;

import java.io.IOException;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000$\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\bf\030\0002\0020\001J\030\020\002\032\0020\0032\006\020\004\032\0020\0052\006\020\006\032\0020\007H&J\030\020\b\032\0020\0032\006\020\004\032\0020\0052\006\020\t\032\0020\nH&¨\006\013"}, d2 = {"Lokhttp3/Callback;", "", "onFailure", "", "call", "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "okhttp"})
public interface Callback {
  void onFailure(@NotNull Call paramCall, @NotNull IOException paramIOException);
  
  void onResponse(@NotNull Call paramCall, @NotNull Response paramResponse) throws IOException;
}


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Callback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
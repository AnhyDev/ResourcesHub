package okhttp3;

import java.net.Socket;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\"\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\bf\030\0002\0020\001J\n\020\002\032\004\030\0010\003H&J\b\020\004\032\0020\005H&J\b\020\006\032\0020\007H&J\b\020\b\032\0020\tH&¨\006\n"}, d2 = {"Lokhttp3/Connection;", "", "handshake", "Lokhttp3/Handshake;", "protocol", "Lokhttp3/Protocol;", "route", "Lokhttp3/Route;", "socket", "Ljava/net/Socket;", "okhttp"})
public interface Connection {
  @NotNull
  Route route();
  
  @NotNull
  Socket socket();
  
  @Nullable
  Handshake handshake();
  
  @NotNull
  Protocol protocol();
}


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Connection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
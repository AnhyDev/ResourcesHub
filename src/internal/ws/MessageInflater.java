/*    */ package okhttp3.internal.ws;
/*    */ 
/*    */ import java.io.Closeable;
/*    */ import java.io.IOException;
/*    */ import java.util.zip.Inflater;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okio.Buffer;
/*    */ import okio.InflaterSource;
/*    */ import okio.Source;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000,\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\003\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\b\020\013\032\0020\fH\026J\016\020\r\032\0020\f2\006\020\016\032\0020\006R\016\020\005\032\0020\006X\004¢\006\002\n\000R\016\020\007\032\0020\bX\004¢\006\002\n\000R\016\020\t\032\0020\nX\004¢\006\002\n\000R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\017"}, d2 = {"Lokhttp3/internal/ws/MessageInflater;", "Ljava/io/Closeable;", "noContextTakeover", "", "(Z)V", "deflatedBytes", "Lokio/Buffer;", "inflater", "Ljava/util/zip/Inflater;", "inflaterSource", "Lokio/InflaterSource;", "close", "", "inflate", "buffer", "okhttp"})
/*    */ public final class MessageInflater
/*    */   implements Closeable
/*    */ {
/*    */   private final Buffer deflatedBytes;
/*    */   private final Inflater inflater;
/*    */   private final InflaterSource inflaterSource;
/*    */   private final boolean noContextTakeover;
/*    */   
/*    */   public MessageInflater(boolean noContextTakeover) {
/* 26 */     this.noContextTakeover = noContextTakeover;
/*    */ 
/*    */     
/* 29 */     this.deflatedBytes = new Buffer();
/* 30 */     this.inflater = new Inflater(true);
/* 31 */     this.inflaterSource = new InflaterSource((Source)this.deflatedBytes, this.inflater);
/*    */   }
/*    */ 
/*    */   
/*    */   public final void inflate(@NotNull Buffer buffer) throws IOException {
/* 36 */     Intrinsics.checkNotNullParameter(buffer, "buffer"); boolean bool1 = (this.deflatedBytes.size() == 0L) ? true : false, bool2 = false, bool3 = false; bool3 = false; boolean bool4 = false; if (!bool1) { boolean bool = false; String str = "Failed requirement."; throw (Throwable)new IllegalArgumentException(str.toString()); }
/*    */     
/* 38 */     if (this.noContextTakeover) {
/* 39 */       this.inflater.reset();
/*    */     }
/*    */     
/* 42 */     this.deflatedBytes.writeAll((Source)buffer);
/* 43 */     this.deflatedBytes.writeInt(65535);
/*    */     
/* 45 */     long totalBytesToRead = this.inflater.getBytesRead() + this.deflatedBytes.size();
/*    */ 
/*    */ 
/*    */     
/*    */     do {
/* 50 */       this.inflaterSource.readOrInflate(buffer, Long.MAX_VALUE);
/* 51 */     } while (this.inflater.getBytesRead() < totalBytesToRead);
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 55 */     this.inflaterSource.close();
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/ws/MessageInflater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
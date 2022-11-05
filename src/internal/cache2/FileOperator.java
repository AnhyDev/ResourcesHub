/*    */ package okhttp3.internal.cache2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.nio.channels.FileChannel;
/*    */ import java.nio.channels.ReadableByteChannel;
/*    */ import java.nio.channels.WritableByteChannel;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okio.Buffer;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000&\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\004\b\000\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\036\020\005\032\0020\0062\006\020\007\032\0020\b2\006\020\t\032\0020\n2\006\020\013\032\0020\bJ\036\020\f\032\0020\0062\006\020\007\032\0020\b2\006\020\r\032\0020\n2\006\020\013\032\0020\bR\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\016"}, d2 = {"Lokhttp3/internal/cache2/FileOperator;", "", "fileChannel", "Ljava/nio/channels/FileChannel;", "(Ljava/nio/channels/FileChannel;)V", "read", "", "pos", "", "sink", "Lokio/Buffer;", "byteCount", "write", "source", "okhttp"})
/*    */ public final class FileOperator
/*    */ {
/*    */   private final FileChannel fileChannel;
/*    */   
/*    */   public FileOperator(@NotNull FileChannel fileChannel) {
/* 32 */     this.fileChannel = fileChannel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void write(long pos, @NotNull Buffer source, long byteCount) throws IOException {
/* 39 */     Intrinsics.checkNotNullParameter(source, "source"); if (byteCount < 0L || byteCount > source.size()) {
/* 40 */       throw (Throwable)new IndexOutOfBoundsException();
/*    */     }
/* 42 */     long mutablePos = pos;
/* 43 */     long mutableByteCount = byteCount;
/*    */     
/* 45 */     while (mutableByteCount > 0L) {
/* 46 */       long bytesWritten = this.fileChannel.transferFrom((ReadableByteChannel)source, mutablePos, mutableByteCount);
/* 47 */       mutablePos += bytesWritten;
/* 48 */       mutableByteCount -= bytesWritten;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void read(long pos, @NotNull Buffer sink, long byteCount) {
/* 58 */     Intrinsics.checkNotNullParameter(sink, "sink"); if (byteCount < 0L) {
/* 59 */       throw (Throwable)new IndexOutOfBoundsException();
/*    */     }
/* 61 */     long mutablePos = pos;
/* 62 */     long mutableByteCount = byteCount;
/*    */     
/* 64 */     while (mutableByteCount > 0L) {
/* 65 */       long bytesRead = this.fileChannel.transferTo(mutablePos, mutableByteCount, (WritableByteChannel)sink);
/* 66 */       mutablePos += bytesRead;
/* 67 */       mutableByteCount -= bytesRead;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/cache2/FileOperator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package okhttp3.internal.ws;
/*    */ 
/*    */ import java.io.Closeable;
/*    */ import java.io.IOException;
/*    */ import java.util.zip.Deflater;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.io.CloseableKt;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okio.Buffer;
/*    */ import okio.ByteString;
/*    */ import okio.DeflaterSink;
/*    */ import okio.Sink;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0002\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\004\n\002\030\002\n\000\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\b\020\013\032\0020\fH\026J\016\020\r\032\0020\f2\006\020\016\032\0020\006J\024\020\017\032\0020\003*\0020\0062\006\020\020\032\0020\021H\002R\016\020\005\032\0020\006X\004¢\006\002\n\000R\016\020\007\032\0020\bX\004¢\006\002\n\000R\016\020\t\032\0020\nX\004¢\006\002\n\000R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\022"}, d2 = {"Lokhttp3/internal/ws/MessageDeflater;", "Ljava/io/Closeable;", "noContextTakeover", "", "(Z)V", "deflatedBytes", "Lokio/Buffer;", "deflater", "Ljava/util/zip/Deflater;", "deflaterSink", "Lokio/DeflaterSink;", "close", "", "deflate", "buffer", "endsWith", "suffix", "Lokio/ByteString;", "okhttp"})
/*    */ public final class MessageDeflater
/*    */   implements Closeable
/*    */ {
/*    */   private final Buffer deflatedBytes;
/*    */   private final Deflater deflater;
/*    */   private final DeflaterSink deflaterSink;
/*    */   private final boolean noContextTakeover;
/*    */   
/*    */   public MessageDeflater(boolean noContextTakeover) {
/* 29 */     this.noContextTakeover = noContextTakeover;
/*    */ 
/*    */     
/* 32 */     this.deflatedBytes = new Buffer();
/* 33 */     this.deflater = new Deflater(-1, true);
/* 34 */     this.deflaterSink = new DeflaterSink((Sink)this.deflatedBytes, this.deflater);
/*    */   }
/*    */ 
/*    */   
/*    */   public final void deflate(@NotNull Buffer buffer) throws IOException {
/* 39 */     Intrinsics.checkNotNullParameter(buffer, "buffer"); boolean bool1 = (this.deflatedBytes.size() == 0L) ? true : false, bool2 = false, bool3 = false; bool3 = false; boolean bool4 = false; if (!bool1) { boolean bool = false; String str = "Failed requirement."; throw (Throwable)new IllegalArgumentException(str.toString()); }
/*    */     
/* 41 */     if (this.noContextTakeover) {
/* 42 */       this.deflater.reset();
/*    */     }
/*    */     
/* 45 */     this.deflaterSink.write(buffer, buffer.size());
/* 46 */     this.deflaterSink.flush();
/*    */     
/* 48 */     if (endsWith(this.deflatedBytes, MessageDeflaterKt.access$getEMPTY_DEFLATE_BLOCK$p()))
/* 49 */     { long newSize = this.deflatedBytes.size() - 4L;
/* 50 */       Closeable closeable = (Closeable)Buffer.readAndWriteUnsafe$default(this.deflatedBytes, null, 1, null); bool4 = false; boolean bool = false; Throwable throwable = (Throwable)null; try { Buffer.UnsafeCursor cursor = (Buffer.UnsafeCursor)closeable; int $i$a$-use-MessageDeflater$deflate$1 = 0; long l = 
/* 51 */           cursor.resizeBuffer(newSize); }
/*    */       catch (Throwable throwable1) { throwable = throwable1 = null; throw throwable1; }
/*    */       finally { CloseableKt.closeFinally(closeable, throwable); }
/*    */        }
/* 55 */     else { this.deflatedBytes.writeByte(0); }
/*    */ 
/*    */     
/* 58 */     buffer.write(this.deflatedBytes, this.deflatedBytes.size());
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 62 */     this.deflaterSink.close();
/*    */   } private final boolean endsWith(Buffer $this$endsWith, ByteString suffix) {
/* 64 */     return $this$endsWith.rangeEquals($this$endsWith.size() - suffix.size(), suffix);
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/ws/MessageDeflater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
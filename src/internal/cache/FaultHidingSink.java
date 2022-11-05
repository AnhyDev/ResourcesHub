/*    */ package okhttp3.internal.cache;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.Unit;
/*    */ import kotlin.jvm.functions.Function1;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okio.Buffer;
/*    */ import okio.ForwardingSink;
/*    */ import okio.Sink;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0004\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\020\002\n\002\b\002\n\002\020\013\n\002\b\006\n\002\030\002\n\000\n\002\020\t\n\000\b\020\030\0002\0020\001B!\022\006\020\002\032\0020\003\022\022\020\004\032\016\022\004\022\0020\006\022\004\022\0020\0070\005¢\006\002\020\bJ\b\020\r\032\0020\007H\026J\b\020\016\032\0020\007H\026J\030\020\017\032\0020\0072\006\020\020\032\0020\0212\006\020\022\032\0020\023H\026R\016\020\t\032\0020\nX\016¢\006\002\n\000R\035\020\004\032\016\022\004\022\0020\006\022\004\022\0020\0070\005¢\006\b\n\000\032\004\b\013\020\f¨\006\024"}, d2 = {"Lokhttp3/internal/cache/FaultHidingSink;", "Lokio/ForwardingSink;", "delegate", "Lokio/Sink;", "onException", "Lkotlin/Function1;", "Ljava/io/IOException;", "", "(Lokio/Sink;Lkotlin/jvm/functions/Function1;)V", "hasErrors", "", "getOnException", "()Lkotlin/jvm/functions/Function1;", "close", "flush", "write", "source", "Lokio/Buffer;", "byteCount", "", "okhttp"})
/*    */ public class FaultHidingSink
/*    */   extends ForwardingSink
/*    */ {
/*    */   private boolean hasErrors;
/*    */   @NotNull
/*    */   private final Function1<IOException, Unit> onException;
/*    */   
/*    */   public FaultHidingSink(@NotNull Sink delegate, @NotNull Function1<IOException, Unit> onException) {
/* 27 */     super(delegate); this.onException = onException;
/*    */   }
/*    */   
/*    */   public void write(@NotNull Buffer source, long byteCount) {
/* 31 */     Intrinsics.checkNotNullParameter(source, "source"); if (this.hasErrors) {
/* 32 */       source.skip(byteCount);
/*    */       return;
/*    */     } 
/*    */     try {
/* 36 */       super.write(source, byteCount);
/* 37 */     } catch (IOException e) {
/* 38 */       this.hasErrors = true;
/* 39 */       this.onException.invoke(e);
/*    */     }  } @NotNull
/*    */   public final Function1<IOException, Unit> getOnException() {
/*    */     return this.onException;
/*    */   } public void flush() {
/* 44 */     if (this.hasErrors) {
/*    */       return;
/*    */     }
/*    */     try {
/* 48 */       super.flush();
/* 49 */     } catch (IOException e) {
/* 50 */       this.hasErrors = true;
/* 51 */       this.onException.invoke(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void close() {
/* 56 */     if (this.hasErrors) {
/*    */       return;
/*    */     }
/*    */     try {
/* 60 */       super.close();
/* 61 */     } catch (IOException e) {
/* 62 */       this.hasErrors = true;
/* 63 */       this.onException.invoke(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/cache/FaultHidingSink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
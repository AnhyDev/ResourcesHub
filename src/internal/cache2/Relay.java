/*     */ package okhttp3.internal.cache2;
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000L\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\n\002\020\013\n\002\b\n\n\002\020\b\n\002\b\016\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\t\030\000 :2\0020\001:\002:;B3\b\002\022\b\020\002\032\004\030\0010\003\022\b\020\004\032\004\030\0010\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\t\022\006\020\n\032\0020\007¢\006\002\020\013J\016\0202\032\002032\006\0204\032\0020\007J\006\020\b\032\0020\tJ\b\0205\032\004\030\0010\005J \0206\032\002032\006\0207\032\0020\t2\006\0204\032\0020\0072\006\0208\032\0020\007H\002J\020\0209\032\002032\006\0204\032\0020\007H\002R\021\020\f\032\0020\r¢\006\b\n\000\032\004\b\016\020\017R\021\020\n\032\0020\007¢\006\b\n\000\032\004\b\020\020\021R\032\020\022\032\0020\023X\016¢\006\016\n\000\032\004\b\024\020\025\"\004\b\026\020\027R\034\020\002\032\004\030\0010\003X\016¢\006\016\n\000\032\004\b\030\020\031\"\004\b\032\020\033R\021\020\034\032\0020\0238F¢\006\006\032\004\b\034\020\025R\016\020\b\032\0020\tX\004¢\006\002\n\000R\032\020\035\032\0020\036X\016¢\006\016\n\000\032\004\b\037\020 \"\004\b!\020\"R\034\020\004\032\004\030\0010\005X\016¢\006\016\n\000\032\004\b#\020$\"\004\b%\020&R\021\020'\032\0020\r¢\006\b\n\000\032\004\b(\020\017R\032\020\006\032\0020\007X\016¢\006\016\n\000\032\004\b)\020\021\"\004\b*\020+R\034\020,\032\004\030\0010-X\016¢\006\016\n\000\032\004\b.\020/\"\004\b0\0201¨\006<"}, d2 = {"Lokhttp3/internal/cache2/Relay;", "", "file", "Ljava/io/RandomAccessFile;", "upstream", "Lokio/Source;", "upstreamPos", "", "metadata", "Lokio/ByteString;", "bufferMaxSize", "(Ljava/io/RandomAccessFile;Lokio/Source;JLokio/ByteString;J)V", "buffer", "Lokio/Buffer;", "getBuffer", "()Lokio/Buffer;", "getBufferMaxSize", "()J", "complete", "", "getComplete", "()Z", "setComplete", "(Z)V", "getFile", "()Ljava/io/RandomAccessFile;", "setFile", "(Ljava/io/RandomAccessFile;)V", "isClosed", "sourceCount", "", "getSourceCount", "()I", "setSourceCount", "(I)V", "getUpstream", "()Lokio/Source;", "setUpstream", "(Lokio/Source;)V", "upstreamBuffer", "getUpstreamBuffer", "getUpstreamPos", "setUpstreamPos", "(J)V", "upstreamReader", "Ljava/lang/Thread;", "getUpstreamReader", "()Ljava/lang/Thread;", "setUpstreamReader", "(Ljava/lang/Thread;)V", "commit", "", "upstreamSize", "newSource", "writeHeader", "prefix", "metadataSize", "writeMetadata", "Companion", "RelaySource", "okhttp"})
/*     */ public final class Relay { @Nullable
/*     */   private Thread upstreamReader; @NotNull
/*     */   private final Buffer upstreamBuffer; private boolean complete;
/*     */   @NotNull
/*     */   private final Buffer buffer;
/*     */   private int sourceCount;
/*     */   @Nullable
/*     */   private RandomAccessFile file;
/*     */   @Nullable
/*     */   private Source upstream;
/*     */   private long upstreamPos;
/*     */   private final ByteString metadata;
/*     */   private final long bufferMaxSize;
/*     */   private static final int SOURCE_UPSTREAM = 1;
/*     */   private static final int SOURCE_FILE = 2;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final ByteString PREFIX_CLEAN;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final ByteString PREFIX_DIRTY;
/*     */   private static final long FILE_HEADER_SIZE = 32L;
/*     */   
/*     */   @Nullable
/*     */   public final RandomAccessFile getFile() {
/*     */     return this.file;
/*     */   }
/*     */   
/*     */   public final void setFile(@Nullable RandomAccessFile <set-?>) {
/*     */     this.file = <set-?>;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public final Source getUpstream() {
/*     */     return this.upstream;
/*     */   }
/*     */   
/*  40 */   private Relay(RandomAccessFile file, Source upstream, long upstreamPos, ByteString metadata, long bufferMaxSize) { this.file = file; this.upstream = upstream; this.upstreamPos = upstreamPos; this.metadata = metadata; this.bufferMaxSize = bufferMaxSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     this.upstreamBuffer = new Buffer();
/*     */ 
/*     */     
/*  81 */     this.complete = (this.upstream == null);
/*     */ 
/*     */     
/*  84 */     this.buffer = new Buffer(); } public final void setUpstream(@Nullable Source <set-?>) { this.upstream = <set-?>; } public final long getUpstreamPos() { return this.upstreamPos; } public final void setUpstreamPos(long <set-?>) { this.upstreamPos = <set-?>; } @NotNull public final Buffer getBuffer() { return this.buffer; } public final long getBufferMaxSize() { return this.bufferMaxSize; }
/*     */   @Nullable public final Thread getUpstreamReader() { return this.upstreamReader; }
/*     */   public final void setUpstreamReader(@Nullable Thread <set-?>) { this.upstreamReader = <set-?>; }
/*     */   @NotNull public final Buffer getUpstreamBuffer() { return this.upstreamBuffer; }
/*     */   public final boolean getComplete() { return this.complete; }
/*     */   public final void setComplete(boolean <set-?>) { this.complete = <set-?>; }
/*  90 */   public final int getSourceCount() { return this.sourceCount; } public final void setSourceCount(int <set-?>) { this.sourceCount = <set-?>; }
/*     */   
/*     */   public final boolean isClosed() {
/*  93 */     return (this.file == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void writeHeader(ByteString prefix, long upstreamSize, long metadataSize) throws IOException {
/* 101 */     Buffer buffer1 = new Buffer(); boolean bool1 = false, bool2 = false; Buffer $this$apply = buffer1; int $i$a$-apply-Relay$writeHeader$header$1 = 0;
/* 102 */     $this$apply.write(prefix);
/* 103 */     $this$apply.writeLong(upstreamSize);
/* 104 */     $this$apply.writeLong(metadataSize);
/* 105 */     boolean bool3 = ($this$apply.size() == 32L) ? true : false, bool4 = false, bool5 = false; bool5 = false; boolean bool6 = false; if (!bool3) { boolean bool = false; String str = "Failed requirement."; throw (Throwable)new IllegalArgumentException(str.toString()); }
/*     */     
/*     */     Buffer header = buffer1;
/* 108 */     Intrinsics.checkNotNull(this.file); Intrinsics.checkNotNullExpressionValue(this.file.getChannel(), "file!!.channel"); FileOperator fileOperator = new FileOperator(this.file.getChannel());
/* 109 */     fileOperator.write(0L, header, 32L);
/*     */   }
/*     */ 
/*     */   
/*     */   private final void writeMetadata(long upstreamSize) throws IOException {
/* 114 */     Buffer metadataBuffer = new Buffer();
/* 115 */     metadataBuffer.write(this.metadata);
/*     */     
/* 117 */     Intrinsics.checkNotNull(this.file); Intrinsics.checkNotNullExpressionValue(this.file.getChannel(), "file!!.channel"); FileOperator fileOperator = new FileOperator(this.file.getChannel());
/* 118 */     fileOperator.write(32L + upstreamSize, metadataBuffer, this.metadata.size());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void commit(long upstreamSize) throws IOException {
/* 124 */     writeMetadata(upstreamSize);
/* 125 */     Intrinsics.checkNotNull(this.file); this.file.getChannel().force(false);
/*     */ 
/*     */     
/* 128 */     writeHeader(PREFIX_CLEAN, upstreamSize, this.metadata.size());
/* 129 */     Intrinsics.checkNotNull(this.file); this.file.getChannel().force(false);
/*     */ 
/*     */     
/* 132 */     Relay relay = this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-Relay$commit$1 = 0;
/* 133 */       this.complete = true;
/* 134 */       Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache2/Relay}, name=null} */ }
/*     */     
/* 136 */     if (this.upstream != null) { Util.closeQuietly((Closeable)this.upstream); } else {  }
/* 137 */      this.upstream = (Source)null;
/*     */   } @NotNull
/*     */   public final ByteString metadata() {
/* 140 */     return this.metadata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public final Source newSource() {
/* 148 */     Relay relay = this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-Relay$newSource$1 = 0;
/* 149 */       if (this.file == null) { Source source = null; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache2/Relay}, name=null} */ return source; }
/* 150 */        int i; this.sourceCount = (i = this.sourceCount) + 1; null = i;
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache2/Relay}, name=null} */ }
/*     */     
/* 153 */     return new RelaySource();
/*     */   } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000.\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\002\b\004\030\0002\0020\001B\005¢\006\002\020\002J\b\020\t\032\0020\nH\026J\030\020\013\032\0020\0062\006\020\f\032\0020\r2\006\020\016\032\0020\006H\026J\b\020\007\032\0020\bH\026R\020\020\003\032\004\030\0010\004X\016¢\006\002\n\000R\016\020\005\032\0020\006X\016¢\006\002\n\000R\016\020\007\032\0020\bX\004¢\006\002\n\000¨\006\017"}, d2 = {"Lokhttp3/internal/cache2/Relay$RelaySource;", "Lokio/Source;", "(Lokhttp3/internal/cache2/Relay;)V", "fileOperator", "Lokhttp3/internal/cache2/FileOperator;", "sourcePos", "", "timeout", "Lokio/Timeout;", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "okhttp"})
/*     */   public final class RelaySource implements Source { private final Timeout timeout; private FileOperator fileOperator; private long sourcePos;
/*     */     public RelaySource() {
/* 157 */       this.timeout = new Timeout();
/*     */ 
/*     */       
/* 160 */       Intrinsics.checkNotNull(Relay.this.getFile()); Intrinsics.checkNotNullExpressionValue(Relay.this.getFile().getChannel(), "file!!.channel"); this.fileOperator = new FileOperator(Relay.this.getFile().getChannel());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long read(@NotNull Buffer sink, long byteCount) throws IOException {
/* 188 */       Intrinsics.checkNotNullParameter(sink, "sink"); boolean bool1 = (this.fileOperator != null) ? true : false, bool2 = false, bool3 = false; bool3 = false; null = false; if (!bool1) { boolean bool = false; String str = "Check failed."; throw (Throwable)new IllegalStateException(str.toString()); }
/*     */       
/* 190 */       Relay relay1 = Relay.this; bool3 = false; synchronized (false) { int $i$a$-synchronized-Relay$RelaySource$read$source$1 = 0;
/*     */         
/*     */         while (true) {
/* 193 */           long upstreamPos = Relay.this.getUpstreamPos();
/* 194 */           if (this.sourcePos != upstreamPos) {
/*     */             break;
/*     */           }
/* 197 */           if (Relay.this.getComplete()) { long l = -1L; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache2/Relay}, name=null} */ return l; }
/*     */ 
/*     */           
/* 200 */           if (Relay.this.getUpstreamReader() != null) {
/* 201 */             this.timeout.waitUntilNotified(Relay.this);
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 206 */           Relay.this.setUpstreamReader(Thread.currentThread());
/*     */         } 
/*     */ 
/*     */         
/* 210 */         long bufferPos = Relay.this.getUpstreamPos() - Relay.this.getBuffer().size();
/*     */ 
/*     */         
/* 213 */         if (this.sourcePos < bufferPos) {
/*     */ 
/*     */         
/*     */         } else {
/*     */           
/* 218 */           long l1 = byteCount, l2 = Relay.this.getUpstreamPos() - this.sourcePos; boolean bool = false; long bytesToRead = Math.min(l1, l2);
/* 219 */           Relay.this.getBuffer().copyTo(sink, this.sourcePos - bufferPos, bytesToRead);
/* 220 */           this.sourcePos += bytesToRead;
/* 221 */           long l3 = bytesToRead; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache2/Relay}, name=null} */ return l3;
/*     */         }  null = true; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache2/Relay}, name=null} */ }
/*     */       
/*     */       int source = null;
/* 225 */       if (source == 2) {
/* 226 */         long l1 = Relay.this.getUpstreamPos() - this.sourcePos; boolean bool = false; long bytesToRead = Math.min(byteCount, l1);
/* 227 */         Intrinsics.checkNotNull(this.fileOperator); this.fileOperator.read(32L + this.sourcePos, sink, bytesToRead);
/* 228 */         this.sourcePos += bytesToRead;
/* 229 */         return bytesToRead;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 235 */       try { Intrinsics.checkNotNull(Relay.this.getUpstream()); long upstreamBytesRead = Relay.this.getUpstream().read(Relay.this.getUpstreamBuffer(), Relay.this.getBufferMaxSize());
/*     */ 
/*     */         
/* 238 */         if (upstreamBytesRead == -1L)
/* 239 */         { Relay.this.commit(Relay.this.getUpstreamPos());
/* 240 */           long l = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 265 */           Relay relay = Relay.this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-Relay$RelaySource$read$2 = 0;
/* 266 */             Relay.this.setUpstreamReader((Thread)null);
/* 267 */             Object $this$notifyAll$iv = Relay.this; int $i$f$notifyAll = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 357 */             if ($this$notifyAll$iv == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");  $this$notifyAll$iv.notifyAll(); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache2/Relay}, name=null} */ }  return l; }  boolean bool5 = false; long bytesRead = Math.min(upstreamBytesRead, byteCount); Relay.this.getUpstreamBuffer().copyTo(sink, 0L, bytesRead); this.sourcePos += bytesRead; Intrinsics.checkNotNull(this.fileOperator); this.fileOperator.write(32L + Relay.this.getUpstreamPos(), Relay.this.getUpstreamBuffer().clone(), upstreamBytesRead); Relay relay3 = Relay.this; boolean bool6 = false; synchronized (false) { int $i$a$-synchronized-Relay$RelaySource$read$1 = 0; Relay.this.getBuffer().write(Relay.this.getUpstreamBuffer(), upstreamBytesRead); if (Relay.this.getBuffer().size() > Relay.this.getBufferMaxSize()) Relay.this.getBuffer().skip(Relay.this.getBuffer().size() - Relay.this.getBufferMaxSize());  Relay.this.setUpstreamPos(Relay.this.getUpstreamPos() + upstreamBytesRead); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache2/Relay}, name=null} */ }  long l1 = bytesRead; Relay relay4 = Relay.this; boolean bool7 = false; synchronized (false) { int $i$a$-synchronized-Relay$RelaySource$read$2 = 0; Relay.this.setUpstreamReader((Thread)null); Object $this$notifyAll$iv = Relay.this; int $i$f$notifyAll = 0; if ($this$notifyAll$iv == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");  $this$notifyAll$iv.notifyAll(); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache2/Relay}, name=null} */ }  return l1; } finally {} Relay relay2 = Relay.this; boolean bool4 = false; synchronized (false) { int $i$a$-synchronized-Relay$RelaySource$read$2 = 0; Relay.this.setUpstreamReader((Thread)null); Object $this$notifyAll$iv = Relay.this; int $i$f$notifyAll = 0; if ($this$notifyAll$iv == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");  $this$notifyAll$iv.notifyAll();
/*     */         Unit unit = Unit.INSTANCE;
/*     */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache2/Relay}, name=null} */ }
/*     */       
/*     */       throw relay1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Timeout timeout() {
/*     */       return this.timeout;
/*     */     }
/*     */     
/*     */     public void close() throws IOException {
/*     */       if (this.fileOperator == null)
/*     */         return; 
/*     */       this.fileOperator = (FileOperator)null;
/*     */       Object fileToClose = null;
/*     */       Relay relay = Relay.this;
/*     */       boolean bool = false;
/*     */       synchronized (false) {
/*     */         int $i$a$-synchronized-Relay$RelaySource$close$1 = 0;
/*     */         int i;
/*     */         Relay.this.setSourceCount((i = Relay.this.getSourceCount()) + -1);
/*     */         if (Relay.this.getSourceCount() == 0) {
/*     */           fileToClose = Relay.this.getFile();
/*     */           Relay.this.setFile((RandomAccessFile)null);
/*     */         } 
/*     */         Unit unit = Unit.INSTANCE;
/*     */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache2/Relay}, name=null} */
/*     */       } 
/*     */       if (fileToClose != null) {
/*     */         Util.closeQuietly((Closeable)fileToClose);
/*     */       } else {
/*     */       
/*     */       } 
/*     */     } }
/*     */   
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   static {
/*     */     PREFIX_CLEAN = ByteString.Companion.encodeUtf8("OkHttp cache v1\n");
/*     */     PREFIX_DIRTY = ByteString.Companion.encodeUtf8("OkHttp DIRTY :(\n");
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0006\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J&\020\013\032\0020\f2\006\020\r\032\0020\0162\006\020\017\032\0020\0202\006\020\021\032\0020\0062\006\020\022\032\0020\004J\016\020\023\032\0020\f2\006\020\r\032\0020\016R\016\020\003\032\0020\004XT¢\006\002\n\000R\020\020\005\032\0020\0068\006X\004¢\006\002\n\000R\020\020\007\032\0020\0068\006X\004¢\006\002\n\000R\016\020\b\032\0020\tXT¢\006\002\n\000R\016\020\n\032\0020\tXT¢\006\002\n\000¨\006\024"}, d2 = {"Lokhttp3/internal/cache2/Relay$Companion;", "", "()V", "FILE_HEADER_SIZE", "", "PREFIX_CLEAN", "Lokio/ByteString;", "PREFIX_DIRTY", "SOURCE_FILE", "", "SOURCE_UPSTREAM", "edit", "Lokhttp3/internal/cache2/Relay;", "file", "Ljava/io/File;", "upstream", "Lokio/Source;", "metadata", "bufferMaxSize", "read", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     @NotNull
/*     */     public final Relay edit(@NotNull File file, @NotNull Source upstream, @NotNull ByteString metadata, long bufferMaxSize) throws IOException {
/*     */       Intrinsics.checkNotNullParameter(file, "file");
/*     */       Intrinsics.checkNotNullParameter(upstream, "upstream");
/*     */       Intrinsics.checkNotNullParameter(metadata, "metadata");
/*     */       RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
/*     */       Relay result = new Relay(randomAccessFile, upstream, 0L, metadata, bufferMaxSize, null);
/*     */       randomAccessFile.setLength(0L);
/*     */       result.writeHeader(Relay.PREFIX_DIRTY, -1L, -1L);
/*     */       return result;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Relay read(@NotNull File file) throws IOException {
/*     */       Intrinsics.checkNotNullParameter(file, "file");
/*     */       RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
/*     */       Intrinsics.checkNotNullExpressionValue(randomAccessFile.getChannel(), "randomAccessFile.channel");
/*     */       FileOperator fileOperator = new FileOperator(randomAccessFile.getChannel());
/*     */       Buffer header = new Buffer();
/*     */       fileOperator.read(0L, header, 32L);
/*     */       ByteString prefix = header.readByteString(Relay.PREFIX_CLEAN.size());
/*     */       if ((Intrinsics.areEqual(prefix, Relay.PREFIX_CLEAN) ^ true) != 0)
/*     */         throw (Throwable)new IOException("unreadable cache file"); 
/*     */       long upstreamSize = header.readLong();
/*     */       long metadataSize = header.readLong();
/*     */       Buffer metadataBuffer = new Buffer();
/*     */       fileOperator.read(32L + upstreamSize, metadataBuffer, metadataSize);
/*     */       ByteString metadata = metadataBuffer.readByteString();
/*     */       return new Relay(randomAccessFile, null, upstreamSize, metadata, 0L, null);
/*     */     }
/*     */   } }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/cache2/Relay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
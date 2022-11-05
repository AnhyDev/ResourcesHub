/*      */ package okhttp3.internal.cache;
/*      */ import java.io.Closeable;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import kotlin.Unit;
/*      */ import kotlin.jvm.JvmField;
/*      */ import kotlin.jvm.internal.Intrinsics;
/*      */ import okio.BufferedSink;
/*      */ import okio.BufferedSource;
/*      */ import okio.Sink;
/*      */ import okio.Source;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000y\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\030\002\n\000\n\002\b\021\n\002\030\002\n\000\n\002\030\002\n\002\020\016\n\002\030\002\n\002\b\017\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\t\n\002\030\002\n\002\b\020\n\002\020)\n\002\b\007*\001\024\030\000 [2\0020\0012\0020\002:\004[\\]^B7\b\000\022\006\020\003\032\0020\004\022\006\020\005\032\0020\006\022\006\020\007\032\0020\b\022\006\020\t\032\0020\b\022\006\020\n\032\0020\013\022\006\020\f\032\0020\r¢\006\002\020\016J\b\0208\032\00209H\002J\b\020:\032\00209H\026J!\020;\032\002092\n\020<\032\0060=R\0020\0002\006\020>\032\0020\020H\000¢\006\002\b?J\006\020@\032\00209J \020A\032\b\030\0010=R\0020\0002\006\020B\032\0020(2\b\b\002\020C\032\0020\013H\007J\006\020D\032\00209J\b\020E\032\00209H\026J\027\020F\032\b\030\0010GR\0020\0002\006\020B\032\0020(H\002J\006\020H\032\00209J\006\020I\032\0020\020J\b\020J\032\0020\020H\002J\b\020K\032\0020%H\002J\b\020L\032\00209H\002J\b\020M\032\00209H\002J\020\020N\032\002092\006\020O\032\0020(H\002J\r\020P\032\00209H\000¢\006\002\bQJ\016\020R\032\0020\0202\006\020B\032\0020(J\031\020S\032\0020\0202\n\020T\032\0060)R\0020\000H\000¢\006\002\bUJ\b\020V\032\0020\020H\002J\006\0205\032\0020\013J\020\020W\032\f\022\b\022\0060GR\0020\0000XJ\006\020Y\032\00209J\020\020Z\032\002092\006\020B\032\0020(H\002R\016\020\007\032\0020\bX\004¢\006\002\n\000R\016\020\017\032\0020\020X\016¢\006\002\n\000R\016\020\021\032\0020\022X\004¢\006\002\n\000R\020\020\023\032\0020\024X\004¢\006\004\n\002\020\025R\032\020\026\032\0020\020X\016¢\006\016\n\000\032\004\b\027\020\030\"\004\b\031\020\032R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\033\020\034R\024\020\003\032\0020\004X\004¢\006\b\n\000\032\004\b\035\020\036R\016\020\037\032\0020\020X\016¢\006\002\n\000R\016\020 \032\0020\020X\016¢\006\002\n\000R\016\020!\032\0020\006X\004¢\006\002\n\000R\016\020\"\032\0020\006X\004¢\006\002\n\000R\016\020#\032\0020\006X\004¢\006\002\n\000R\020\020$\032\004\030\0010%X\016¢\006\002\n\000R$\020&\032\022\022\004\022\0020(\022\b\022\0060)R\0020\0000'X\004¢\006\b\n\000\032\004\b*\020+R&\020\n\032\0020\0132\006\020,\032\0020\0138F@FX\016¢\006\016\n\000\032\004\b-\020.\"\004\b/\0200R\016\0201\032\0020\020X\016¢\006\002\n\000R\016\0202\032\0020\020X\016¢\006\002\n\000R\016\0203\032\0020\013X\016¢\006\002\n\000R\016\0204\032\0020\bX\016¢\006\002\n\000R\016\0205\032\0020\013X\016¢\006\002\n\000R\024\020\t\032\0020\bX\004¢\006\b\n\000\032\004\b6\0207¨\006_"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "fileSystem", "Lokhttp3/internal/io/FileSystem;", "directory", "Ljava/io/File;", "appVersion", "", "valueCount", "maxSize", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "(Lokhttp3/internal/io/FileSystem;Ljava/io/File;IIJLokhttp3/internal/concurrent/TaskRunner;)V", "civilizedFileSystem", "", "cleanupQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "cleanupTask", "okhttp3/internal/cache/DiskLruCache$cleanupTask$1", "Lokhttp3/internal/cache/DiskLruCache$cleanupTask$1;", "closed", "getClosed$okhttp", "()Z", "setClosed$okhttp", "(Z)V", "getDirectory", "()Ljava/io/File;", "getFileSystem$okhttp", "()Lokhttp3/internal/io/FileSystem;", "hasJournalErrors", "initialized", "journalFile", "journalFileBackup", "journalFileTmp", "journalWriter", "Lokio/BufferedSink;", "lruEntries", "Ljava/util/LinkedHashMap;", "", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "getLruEntries$okhttp", "()Ljava/util/LinkedHashMap;", "value", "getMaxSize", "()J", "setMaxSize", "(J)V", "mostRecentRebuildFailed", "mostRecentTrimFailed", "nextSequenceNumber", "redundantOpCount", "size", "getValueCount$okhttp", "()I", "checkNotClosed", "", "close", "completeEdit", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "success", "completeEdit$okhttp", "delete", "edit", "key", "expectedSequenceNumber", "evictAll", "flush", "get", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "initialize", "isClosed", "journalRebuildRequired", "newJournalWriter", "processJournal", "readJournal", "readJournalLine", "line", "rebuildJournal", "rebuildJournal$okhttp", "remove", "removeEntry", "entry", "removeEntry$okhttp", "removeOldestEntry", "snapshots", "", "trimToSize", "validateKey", "Companion", "Editor", "Entry", "Snapshot", "okhttp"}) public final class DiskLruCache implements Closeable, Flushable { private long maxSize; private final File journalFile; private final File journalFileTmp; private final File journalFileBackup; private long size; private BufferedSink journalWriter; @NotNull private final LinkedHashMap<String, Entry> lruEntries; private int redundantOpCount; private boolean hasJournalErrors; private boolean civilizedFileSystem; private boolean initialized; private boolean closed; private boolean mostRecentTrimFailed; private boolean mostRecentRebuildFailed; private long nextSequenceNumber; private final TaskQueue cleanupQueue; private final DiskLruCache$cleanupTask$1 cleanupTask; @NotNull private final FileSystem fileSystem; @NotNull private final File directory; private final int appVersion; private final int valueCount; @JvmField @NotNull public static final String JOURNAL_FILE; @JvmField @NotNull public static final String JOURNAL_FILE_TEMP; @JvmField @NotNull public static final String JOURNAL_FILE_BACKUP; @JvmField @NotNull public static final String MAGIC; @JvmField @NotNull public static final String VERSION_1; @JvmField public static final long ANY_SEQUENCE_NUMBER; @JvmField @NotNull public static final Regex LEGAL_KEY_PATTERN; @JvmField @NotNull public static final String CLEAN; @JvmField @NotNull public static final String DIRTY; @JvmField @NotNull public static final String REMOVE; @JvmField @NotNull public static final String READ; @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3, d1 = {"\000\016\n\000\n\002\020\002\n\000\n\002\030\002\n\000\020\000\032\0020\0012\006\020\002\032\0020\003H\n¢\006\002\b\004"}, d2 = {"<anonymous>", "", "it", "Ljava/io/IOException;", "invoke"}) static final class DiskLruCache$newJournalWriter$faultHidingSink$1 extends Lambda implements Function1<IOException, Unit> { public final void invoke(@NotNull IOException it) { Intrinsics.checkNotNullParameter(it, "it"); Object $this$assertThreadHoldsLock$iv = DiskLruCache.this; int $i$f$assertThreadHoldsLock = 0; if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
/*      */         Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv);
/*      */       }  DiskLruCache.this.hasJournalErrors = true; } DiskLruCache$newJournalWriter$faultHidingSink$1() { super(1); } } @NotNull public final FileSystem getFileSystem$okhttp() { return this.fileSystem; } @NotNull public final File getDirectory() { return this.directory; } public final int getValueCount$okhttp() { return this.valueCount; } public final synchronized long getMaxSize() { return this.maxSize; } public final synchronized void setMaxSize(long value) { this.maxSize = value; if (this.initialized)
/*      */       TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);  } @NotNull public final LinkedHashMap<String, Entry> getLruEntries$okhttp() { return this.lruEntries; } public final boolean getClosed$okhttp() { return this.closed; } public final void setClosed$okhttp(boolean <set-?>) { this.closed = <set-?>; } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\021\n\000\n\002\030\002\n\000\n\002\020\t\n\000*\001\000\b\n\030\0002\0020\001J\b\020\002\032\0020\003H\026¨\006\004"}, d2 = {"okhttp3/internal/cache/DiskLruCache$cleanupTask$1", "Lokhttp3/internal/concurrent/Task;", "runOnce", "", "okhttp"}) public static final class DiskLruCache$cleanupTask$1 extends Task { DiskLruCache$cleanupTask$1(String $super_call_param$1) { super($super_call_param$1, false, 2, null); } public long runOnce() { DiskLruCache diskLruCache = DiskLruCache.this; boolean bool = false; synchronized (false) {
/*      */         int $i$a$-synchronized-DiskLruCache$cleanupTask$1$runOnce$1 = 0; if (!DiskLruCache.this.initialized || DiskLruCache.this.getClosed$okhttp()) {
/*      */           long l1 = -1L; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache/DiskLruCache}, name=null} */ return l1;
/*      */         }  try {
/*      */           DiskLruCache.this.trimToSize();
/*      */         } catch (IOException _) {
/*      */           DiskLruCache.this.mostRecentTrimFailed = true;
/*      */         }  try {
/*      */           if (DiskLruCache.this.journalRebuildRequired()) {
/*      */             DiskLruCache.this.rebuildJournal$okhttp(); DiskLruCache.this.redundantOpCount = 0;
/*      */           } 
/*      */         } catch (IOException _) {
/*      */           DiskLruCache.this.mostRecentRebuildFailed = true; DiskLruCache.this.journalWriter = Okio.buffer(Okio.blackhole());
/*      */         }  long l = -1L; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache/DiskLruCache}, name=null} */ return l;
/*      */       }  } } public final synchronized void initialize() throws IOException { Object $this$assertThreadHoldsLock$iv = this; int $i$f$assertThreadHoldsLock = 0; if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
/*      */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv);
/*      */     }  if (this.initialized)
/*      */       return;  if (this.fileSystem.exists(this.journalFileBackup))
/*      */       if (this.fileSystem.exists(this.journalFile)) {
/*      */         this.fileSystem.delete(this.journalFileBackup);
/*      */       } else {
/*      */         this.fileSystem.rename(this.journalFileBackup, this.journalFile);
/*      */       }   this.civilizedFileSystem = Util.isCivilized(this.fileSystem, this.journalFileBackup); if (this.fileSystem.exists(this.journalFile))
/*      */       try {
/*      */         readJournal(); processJournal(); this.initialized = true; return;
/*      */       } catch (IOException journalIsCorrupt) {
/*      */         Platform.Companion.get().log("DiskLruCache " + this.directory + " is corrupt: " + journalIsCorrupt.getMessage() + ", removing", 5, journalIsCorrupt); try {
/*      */           delete();
/*      */         } finally {
/*      */           this.closed = false;
/*      */         } 
/*      */       }   rebuildJournal$okhttp(); this.initialized = true; } private final void readJournal() throws IOException { Closeable closeable = (Closeable)Okio.buffer(this.fileSystem.source(this.journalFile)); boolean bool1 = false, bool2 = false; Throwable throwable = (Throwable)null; try {
/*      */       BufferedSource source = (BufferedSource)closeable; int $i$a$-use-DiskLruCache$readJournal$1 = 0;
/*      */       String magic = source.readUtf8LineStrict();
/*      */       String version = source.readUtf8LineStrict();
/*      */       String appVersionString = source.readUtf8LineStrict();
/*      */       String valueCountString = source.readUtf8LineStrict();
/*      */       String blank = source.readUtf8LineStrict();
/*      */       if ((Intrinsics.areEqual(MAGIC, magic) ^ true) == 0 && (Intrinsics.areEqual(VERSION_1, version) ^ true) == 0 && (Intrinsics.areEqual(String.valueOf(this.appVersion), appVersionString) ^ true) == 0 && (Intrinsics.areEqual(String.valueOf(this.valueCount), valueCountString) ^ true) == 0) {
/*      */         String str = blank;
/*      */         boolean bool = false;
/*      */         if (!((str.length() > 0) ? 1 : 0)) {
/*      */           int lineCount = 0;
/*      */           while (true) {
/*      */             try {
/*      */               readJournalLine(source.readUtf8LineStrict());
/*      */               lineCount++;
/*      */             } catch (EOFException _) {
/*      */               break;
/*      */             } 
/*      */           } 
/*      */           this.redundantOpCount = lineCount - this.lruEntries.size();
/*      */           if (!source.exhausted()) {
/*      */             rebuildJournal$okhttp();
/*      */           } else {
/*      */             this.journalWriter = newJournalWriter();
/*      */           } 
/*      */           Unit unit = Unit.INSTANCE;
/*      */           return;
/*      */         } 
/*      */       } 
/*      */       throw (Throwable)new IOException("unexpected journal header: [" + magic + ", " + version + ", " + valueCountString + ", " + blank + ']');
/*      */     } catch (Throwable throwable1) {
/*      */       throwable = throwable1 = null;
/*      */       throw throwable1;
/*      */     } finally {
/*      */       CloseableKt.closeFinally(closeable, throwable);
/*   87 */     }  } public DiskLruCache(@NotNull FileSystem fileSystem, @NotNull File directory, int appVersion, int valueCount, long maxSize, @NotNull TaskRunner taskRunner) { this.fileSystem = fileSystem; this.directory = directory; this.appVersion = appVersion; this.valueCount = valueCount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  104 */     this.maxSize = maxSize;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  157 */     this.lruEntries = new LinkedHashMap<>(0, 0.75F, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  175 */     this.cleanupQueue = taskRunner.newQueue();
/*  176 */     this.cleanupTask = new DiskLruCache$cleanupTask$1(Util.okHttpName + " Cache");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  205 */     boolean bool1 = (maxSize > 0L) ? true : false, bool2 = false, bool3 = false; if (!bool1)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1070 */       int $i$a$-require-DiskLruCache$1 = 0; String str = "maxSize <= 0"; throw (Throwable)new IllegalArgumentException(str.toString()); }  bool1 = (this.valueCount > 0) ? true : false; bool2 = false; bool3 = false; if (!bool1) { int $i$a$-require-DiskLruCache$2 = 0; String str = "valueCount <= 0"; throw (Throwable)new IllegalArgumentException(str.toString()); }  this.journalFile = new File(this.directory, JOURNAL_FILE); this.journalFileTmp = new File(this.directory, JOURNAL_FILE_TEMP); this.journalFileBackup = new File(this.directory, JOURNAL_FILE_BACKUP); } private final BufferedSink newJournalWriter() throws FileNotFoundException { Sink fileSink = this.fileSystem.appendingSink(this.journalFile); FaultHidingSink faultHidingSink = new FaultHidingSink(fileSink, new DiskLruCache$newJournalWriter$faultHidingSink$1()); return Okio.buffer((Sink)faultHidingSink); } private final void readJournalLine(String line) throws IOException { int firstSpace = StringsKt.indexOf$default(line, ' ', 0, false, 6, null); if (firstSpace == -1) throw (Throwable)new IOException("unexpected journal line: " + line);  int keyBegin = firstSpace + 1; int secondSpace = StringsKt.indexOf$default(line, ' ', keyBegin, false, 4, null); String key = null; if (secondSpace == -1) { String str = line; boolean bool = false; if (str == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str.substring(keyBegin), "(this as java.lang.String).substring(startIndex)"); key = str.substring(keyBegin); if (firstSpace == REMOVE.length() && StringsKt.startsWith$default(line, REMOVE, false, 2, null)) { this.lruEntries.remove(key); return; }  } else { String str = line; boolean bool = false; if (str == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str.substring(keyBegin, secondSpace), "(this as java.lang.Strin…ing(startIndex, endIndex)"); key = str.substring(keyBegin, secondSpace); }  Entry entry = this.lruEntries.get(key); if (entry == null) { entry = new Entry(key); this.lruEntries.put(key, entry); }  if (secondSpace != -1 && firstSpace == CLEAN.length() && StringsKt.startsWith$default(line, CLEAN, false, 2, null)) { String str = line; int i = secondSpace + 1; boolean bool = false; if (str == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str.substring(i), "(this as java.lang.String).substring(startIndex)"); List<String> parts = StringsKt.split$default(str.substring(i), new char[] { ' ' }, false, 0, 6, null); entry.setReadable$okhttp(true); entry.setCurrentEditor$okhttp((Editor)null); entry.setLengths$okhttp(parts); } else if (secondSpace == -1 && firstSpace == DIRTY.length() && StringsKt.startsWith$default(line, DIRTY, false, 2, null)) { entry.setCurrentEditor$okhttp(new Editor(entry)); } else if (secondSpace != -1 || firstSpace != READ.length() || !StringsKt.startsWith$default(line, READ, false, 2, null)) { throw (Throwable)new IOException("unexpected journal line: " + line); }  } private final void processJournal() throws IOException { this.fileSystem.delete(this.journalFileTmp); Iterator i = this.lruEntries.values().iterator(); while (i.hasNext()) { Intrinsics.checkNotNullExpressionValue(i.next(), "i.next()"); Entry entry = (Entry)i.next(); if (entry.getCurrentEditor$okhttp() == null) { byte b; int k; for (b = 0, k = this.valueCount; b < k; b++) this.size += entry.getLengths$okhttp()[b];  continue; }  entry.setCurrentEditor$okhttp((Editor)null); for (int t = 0, j = this.valueCount; t < j; t++) { this.fileSystem.delete(entry.getCleanFiles$okhttp().get(t)); this.fileSystem.delete(entry.getDirtyFiles$okhttp().get(t)); }  i.remove(); }  } public final synchronized void rebuildJournal$okhttp() throws IOException { if (this.journalWriter != null) { this.journalWriter.close(); } else {  }  Closeable closeable = (Closeable)Okio.buffer(this.fileSystem.sink(this.journalFileTmp)); boolean bool1 = false, bool2 = false; Throwable throwable = (Throwable)null; try { BufferedSink sink = (BufferedSink)closeable; int $i$a$-use-DiskLruCache$rebuildJournal$1 = 0; sink.writeUtf8(MAGIC).writeByte(10); sink.writeUtf8(VERSION_1).writeByte(10); sink.writeDecimalLong(this.appVersion).writeByte(10); sink.writeDecimalLong(this.valueCount).writeByte(10); sink.writeByte(10); for (Entry entry : this.lruEntries.values()) { if (entry.getCurrentEditor$okhttp() != null) { sink.writeUtf8(DIRTY).writeByte(32); sink.writeUtf8(entry.getKey$okhttp()); sink.writeByte(10); continue; }  sink.writeUtf8(CLEAN).writeByte(32); sink.writeUtf8(entry.getKey$okhttp()); entry.writeLengths$okhttp(sink); sink.writeByte(10); }  Unit unit = Unit.INSTANCE; } catch (Throwable throwable1) { throwable = throwable1 = null; throw throwable1; } finally { CloseableKt.closeFinally(closeable, throwable); }  if (this.fileSystem.exists(this.journalFile)) this.fileSystem.rename(this.journalFile, this.journalFileBackup);  this.fileSystem.rename(this.journalFileTmp, this.journalFile); this.fileSystem.delete(this.journalFileBackup); this.journalWriter = newJournalWriter(); this.hasJournalErrors = false; this.mostRecentRebuildFailed = false; } @Nullable public final synchronized Snapshot get(@NotNull String key) throws IOException { Intrinsics.checkNotNullParameter(key, "key"); initialize(); checkNotClosed(); validateKey(key); if ((Entry)this.lruEntries.get(key) != null) { Intrinsics.checkNotNullExpressionValue(this.lruEntries.get(key), "lruEntries[key] ?: return null"); Entry entry = this.lruEntries.get(key); if (entry.snapshot$okhttp() != null) { Snapshot snapshot = entry.snapshot$okhttp(); int i; this.redundantOpCount = (i = this.redundantOpCount) + 1; Intrinsics.checkNotNull(this.journalWriter); this.journalWriter.writeUtf8(READ).writeByte(32).writeUtf8(key).writeByte(10); if (journalRebuildRequired()) TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);  return snapshot; }  entry.snapshot$okhttp(); return null; }  (Entry)this.lruEntries.get(key); return null; } @JvmOverloads @Nullable public final synchronized Editor edit(@NotNull String key, long expectedSequenceNumber) throws IOException { Intrinsics.checkNotNullParameter(key, "key"); initialize(); checkNotClosed(); validateKey(key); Entry entry = this.lruEntries.get(key); if (expectedSequenceNumber != ANY_SEQUENCE_NUMBER && (entry == null || entry.getSequenceNumber$okhttp() != expectedSequenceNumber)) return null;  if (((entry != null) ? entry.getCurrentEditor$okhttp() : null) != null) return null;  if (entry != null && entry.getLockingSourceCount$okhttp() != 0) return null;  if (this.mostRecentTrimFailed || this.mostRecentRebuildFailed) { TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null); return null; }  Intrinsics.checkNotNull(this.journalWriter); BufferedSink journalWriter = this.journalWriter; journalWriter.writeUtf8(DIRTY).writeByte(32).writeUtf8(key).writeByte(10); journalWriter.flush(); if (this.hasJournalErrors) return null;  if (entry == null) { entry = new Entry(key); this.lruEntries.put(key, entry); }  Editor editor = new Editor(entry); entry.setCurrentEditor$okhttp(editor); return editor; } public final synchronized long size() throws IOException { initialize(); return this.size; } public final synchronized void completeEdit$okhttp(@NotNull Editor editor, boolean success) throws IOException { Intrinsics.checkNotNullParameter(editor, "editor"); Entry entry = editor.getEntry$okhttp(); boolean bool = Intrinsics.areEqual(entry.getCurrentEditor$okhttp(), editor); int j = 0; boolean bool1 = false; bool1 = false; boolean bool2 = false; if (!bool) { boolean bool3 = false; String str = "Check failed."; throw (Throwable)new IllegalStateException(str.toString()); }  if (success && !entry.getReadable$okhttp()) for (bool = false, j = this.valueCount; bool < j; bool++) { Intrinsics.checkNotNull(editor.getWritten$okhttp()); if (!editor.getWritten$okhttp()[bool]) { editor.abort(); throw (Throwable)new IllegalStateException("Newly created entry didn't create value for index " + bool); }  if (!this.fileSystem.exists(entry.getDirtyFiles$okhttp().get(bool))) { editor.abort(); return; }  }   int i; for (i = 0, j = this.valueCount; i < j; i++) { File dirty = entry.getDirtyFiles$okhttp().get(i); if (success && !entry.getZombie$okhttp()) { if (this.fileSystem.exists(dirty)) { File clean = entry.getCleanFiles$okhttp().get(i); this.fileSystem.rename(dirty, clean); long oldLength = entry.getLengths$okhttp()[i]; long newLength = this.fileSystem.size(clean); entry.getLengths$okhttp()[i] = newLength; this.size = this.size - oldLength + newLength; }  } else { this.fileSystem.delete(dirty); }  }  entry.setCurrentEditor$okhttp((Editor)null); if (entry.getZombie$okhttp()) { removeEntry$okhttp(entry); return; }  this.redundantOpCount = (i = this.redundantOpCount) + 1; Intrinsics.checkNotNull(this.journalWriter); BufferedSink bufferedSink1 = this.journalWriter; j = 0; bool1 = false; BufferedSink $this$apply = bufferedSink1; int $i$a$-apply-DiskLruCache$completeEdit$1 = 0; if (entry.getReadable$okhttp() || success) { entry.setReadable$okhttp(true); $this$apply.writeUtf8(CLEAN).writeByte(32); $this$apply.writeUtf8(entry.getKey$okhttp()); entry.writeLengths$okhttp($this$apply); $this$apply.writeByte(10); if (success) { long l; this.nextSequenceNumber = (l = this.nextSequenceNumber) + 1L; entry.setSequenceNumber$okhttp(l); }  } else { this.lruEntries.remove(entry.getKey$okhttp()); $this$apply.writeUtf8(REMOVE).writeByte(32); $this$apply.writeUtf8(entry.getKey$okhttp()); $this$apply.writeByte(10); }  $this$apply.flush(); if (this.size > this.maxSize || journalRebuildRequired()) TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);  } private final boolean journalRebuildRequired() { int redundantOpCompactThreshold = 2000; return (this.redundantOpCount >= redundantOpCompactThreshold && this.redundantOpCount >= this.lruEntries.size()); } public final synchronized boolean remove(@NotNull String key) throws IOException { Intrinsics.checkNotNullParameter(key, "key"); initialize(); checkNotClosed(); validateKey(key); if ((Entry)this.lruEntries.get(key) != null) { Intrinsics.checkNotNullExpressionValue(this.lruEntries.get(key), "lruEntries[key] ?: return false"); Entry entry = this.lruEntries.get(key); boolean removed = removeEntry$okhttp(entry); if (removed && this.size <= this.maxSize) this.mostRecentTrimFailed = false;  return removed; }  (Entry)this.lruEntries.get(key); return false; } public final boolean removeEntry$okhttp(@NotNull Entry entry) throws IOException { Intrinsics.checkNotNullParameter(entry, "entry"); if (!this.civilizedFileSystem) { if (entry.getLockingSourceCount$okhttp() > 0) { BufferedSink bufferedSink1 = this.journalWriter; boolean bool1 = false, bool2 = false; BufferedSink it = bufferedSink1; int $i$a$-let-DiskLruCache$removeEntry$1 = 0; it.writeUtf8(DIRTY); it.writeByte(32); it.writeUtf8(entry.getKey$okhttp()); it.writeByte(10); it.flush(); }  if (entry.getLockingSourceCount$okhttp() > 0 || entry.getCurrentEditor$okhttp() != null) { entry.setZombie$okhttp(true); return true; }  }  if (entry.getCurrentEditor$okhttp() != null) { entry.getCurrentEditor$okhttp().detach$okhttp(); } else { entry.getCurrentEditor$okhttp(); }  int j; for (byte b = 0; b < j; b++) { this.fileSystem.delete(entry.getCleanFiles$okhttp().get(b)); this.size -= entry.getLengths$okhttp()[b]; entry.getLengths$okhttp()[b] = 0L; }  int i; this.redundantOpCount = (i = this.redundantOpCount) + 1; if (this.journalWriter != null) { BufferedSink bufferedSink1 = this.journalWriter; j = 0; boolean bool = false; BufferedSink it = bufferedSink1; int $i$a$-let-DiskLruCache$removeEntry$2 = 0; it.writeUtf8(REMOVE); it.writeByte(32); it.writeUtf8(entry.getKey$okhttp()); it.writeByte(10); } else {  }  this.lruEntries.remove(entry.getKey$okhttp()); if (journalRebuildRequired()) TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);  return true; } private final synchronized void checkNotClosed() { boolean bool1 = !this.closed ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-DiskLruCache$checkNotClosed$1 = 0; String str = "cache is closed"; throw (Throwable)new IllegalStateException(str.toString()); }  } public synchronized void flush() throws IOException { if (!this.initialized) return;  checkNotClosed(); trimToSize(); Intrinsics.checkNotNull(this.journalWriter); this.journalWriter.flush(); } public final synchronized boolean isClosed() { return this.closed; }
/* 1071 */   public synchronized void close() throws IOException { if (!this.initialized || this.closed) { this.closed = true; return; }  Intrinsics.checkNotNullExpressionValue(this.lruEntries.values(), "lruEntries.values"); Collection<Entry> $this$toTypedArray$iv = this.lruEntries.values(); int $i$f$toTypedArray = 0; Collection<Entry> thisCollection$iv = $this$toTypedArray$iv;
/* 1072 */     if (thisCollection$iv.toArray(new Entry[0]) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");  Entry[] arrayOfEntry = thisCollection$iv.toArray(new Entry[0]); int i = arrayOfEntry.length; byte b = 0; while (b < i) { Entry entry = arrayOfEntry[b]; if (entry.getCurrentEditor$okhttp() != null) if (entry.getCurrentEditor$okhttp() != null) { entry.getCurrentEditor$okhttp().detach$okhttp(); } else { entry.getCurrentEditor$okhttp(); }   b++; }  trimToSize(); Intrinsics.checkNotNull(this.journalWriter); this.journalWriter.close(); this.journalWriter = (BufferedSink)null; this.closed = true; } public final void trimToSize() throws IOException { while (this.size > this.maxSize) { if (!removeOldestEntry()) return;  }  this.mostRecentTrimFailed = false; } private final boolean removeOldestEntry() { for (Entry toEvict : this.lruEntries.values()) { if (!toEvict.getZombie$okhttp()) { Intrinsics.checkNotNullExpressionValue(toEvict, "toEvict"); removeEntry$okhttp(toEvict); return true; }  }  return false; } public final void delete() throws IOException { close(); this.fileSystem.deleteContents(this.directory); }
/* 1073 */   public final synchronized void evictAll() throws IOException { initialize(); Intrinsics.checkNotNullExpressionValue(this.lruEntries.values(), "lruEntries.values"); Collection<Entry> $this$toTypedArray$iv = this.lruEntries.values(); int $i$f$toTypedArray = 0; Collection<Entry> thisCollection$iv = $this$toTypedArray$iv;
/* 1074 */     if (thisCollection$iv.toArray(new Entry[0]) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");  Entry[] arrayOfEntry = thisCollection$iv.toArray(new Entry[0]); int i = arrayOfEntry.length; byte b = 0;
/*      */     while (b < i) {
/*      */       Entry entry = arrayOfEntry[b];
/*      */       Intrinsics.checkNotNullExpressionValue(entry, "entry");
/*      */       removeEntry$okhttp(entry);
/*      */       b++;
/*      */     } 
/*      */     this.mostRecentTrimFailed = false; }
/*      */ 
/*      */   
/*      */   private final void validateKey(String key) {
/*      */     boolean bool = LEGAL_KEY_PATTERN.matches(key);
/*      */     boolean bool1 = false, bool2 = false;
/*      */     if (!bool) {
/*      */       int $i$a$-require-DiskLruCache$validateKey$1 = 0;
/*      */       String str = "keys must match regex [a-z0-9_-]{1,120}: \"" + key + '"';
/*      */       throw (Throwable)new IllegalArgumentException(str.toString());
/*      */     } 
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public final synchronized Iterator<Snapshot> snapshots() throws IOException {
/*      */     initialize();
/*      */     return new DiskLruCache$snapshots$1();
/*      */   }
/*      */   
/*      */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000)\n\000\n\002\020)\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\020\013\n\002\b\002\n\002\020\002\n\000*\001\000\b\n\030\0002\f\022\b\022\0060\002R\0020\0030\001J\t\020\t\032\0020\nH\002J\r\020\013\032\0060\002R\0020\003H\002J\b\020\f\032\0020\rH\026R$\020\004\032\030\022\024\022\022 \006*\b\030\0010\005R\0020\0030\005R\0020\0030\001X\004¢\006\002\n\000R\024\020\007\032\b\030\0010\002R\0020\003X\016¢\006\002\n\000R\024\020\b\032\b\030\0010\002R\0020\003X\016¢\006\002\n\000¨\006\016"}, d2 = {"okhttp3/internal/cache/DiskLruCache$snapshots$1", "", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "delegate", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "kotlin.jvm.PlatformType", "nextSnapshot", "removeSnapshot", "hasNext", "", "next", "remove", "", "okhttp"})
/*      */   public static final class DiskLruCache$snapshots$1 implements Iterator<Snapshot>, KMutableIterator {
/*      */     private final Iterator<DiskLruCache.Entry> delegate;
/*      */     private DiskLruCache.Snapshot nextSnapshot;
/*      */     private DiskLruCache.Snapshot removeSnapshot;
/*      */     
/*      */     DiskLruCache$snapshots$1() {
/*      */       Intrinsics.checkNotNullExpressionValue((new ArrayList(DiskLruCache.this.getLruEntries$okhttp().values())).iterator(), "ArrayList(lruEntries.values).iterator()");
/*      */       this.delegate = (Iterator)(new ArrayList(DiskLruCache.this.getLruEntries$okhttp().values())).iterator();
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*      */       if (this.nextSnapshot != null)
/*      */         return true; 
/*      */       DiskLruCache diskLruCache = DiskLruCache.this;
/*      */       boolean bool = false;
/*      */       synchronized (false) {
/*      */         int $i$a$-synchronized-DiskLruCache$snapshots$1$hasNext$1 = 0;
/*      */         if (DiskLruCache.this.getClosed$okhttp()) {
/*      */           boolean bool1 = false;
/*      */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache/DiskLruCache}, name=null} */
/*      */           return bool1;
/*      */         } 
/*      */         while (this.delegate.hasNext()) {
/*      */           if ((DiskLruCache.Entry)this.delegate.next() != null && ((DiskLruCache.Entry)this.delegate.next()).snapshot$okhttp() != null) {
/*      */             this.nextSnapshot = ((DiskLruCache.Entry)this.delegate.next()).snapshot$okhttp();
/*      */             boolean bool1 = true;
/*      */             /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache/DiskLruCache}, name=null} */
/*      */             return bool1;
/*      */           } 
/*      */           ((DiskLruCache.Entry)this.delegate.next()).snapshot$okhttp();
/*      */           this;
/*      */         } 
/*      */         Unit unit = Unit.INSTANCE;
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache/DiskLruCache}, name=null} */
/*      */       } 
/*      */       return false;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public DiskLruCache.Snapshot next() {
/*      */       if (!hasNext())
/*      */         throw (Throwable)new NoSuchElementException(); 
/*      */       this.removeSnapshot = this.nextSnapshot;
/*      */       this.nextSnapshot = (DiskLruCache.Snapshot)null;
/*      */       Intrinsics.checkNotNull(this.removeSnapshot);
/*      */       return this.removeSnapshot;
/*      */     }
/*      */     
/*      */     public void remove() {
/*      */       DiskLruCache.Snapshot removeSnapshot = this.removeSnapshot;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       if (removeSnapshot == null) {
/*      */         int $i$a$-checkNotNull-DiskLruCache$snapshots$1$remove$1 = 0;
/*      */         String str = "remove() before next()";
/*      */         throw (Throwable)new IllegalStateException(str.toString());
/*      */       } 
/*      */       try {
/*      */         DiskLruCache.this.remove(removeSnapshot.key());
/*      */       } catch (IOException iOException) {
/*      */       
/*      */       } finally {
/*      */         this.removeSnapshot = (DiskLruCache.Snapshot)null;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000B\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020\t\n\000\n\002\020 \n\002\030\002\n\000\n\002\020\026\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\002\b\004\030\0002\0020\001B-\b\000\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\f\020\006\032\b\022\004\022\0020\b0\007\022\006\020\t\032\0020\n¢\006\002\020\013J\b\020\f\032\0020\rH\026J\f\020\016\032\b\030\0010\017R\0020\020J\016\020\021\032\0020\0052\006\020\022\032\0020\023J\016\020\024\032\0020\b2\006\020\022\032\0020\023J\006\020\002\032\0020\003R\016\020\002\032\0020\003X\004¢\006\002\n\000R\016\020\t\032\0020\nX\004¢\006\002\n\000R\016\020\004\032\0020\005X\004¢\006\002\n\000R\024\020\006\032\b\022\004\022\0020\b0\007X\004¢\006\002\n\000¨\006\025"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Ljava/io/Closeable;", "key", "", "sequenceNumber", "", "sources", "", "Lokio/Source;", "lengths", "", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;JLjava/util/List;[J)V", "close", "", "edit", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getLength", "index", "", "getSource", "okhttp"})
/*      */   public final class Snapshot implements Closeable {
/*      */     private final String key;
/*      */     private final long sequenceNumber;
/*      */     private final List<Source> sources;
/*      */     private final long[] lengths;
/*      */     
/*      */     public Snapshot(String key, @NotNull long sequenceNumber, @NotNull List<Source> sources, long[] lengths) {
/*      */       this.key = key;
/*      */       this.sequenceNumber = sequenceNumber;
/*      */       this.sources = sources;
/*      */       this.lengths = lengths;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final String key() {
/*      */       return this.key;
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     public final DiskLruCache.Editor edit() throws IOException {
/*      */       return DiskLruCache.this.edit(this.key, this.sequenceNumber);
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Source getSource(int index) {
/*      */       return this.sources.get(index);
/*      */     }
/*      */     
/*      */     public final long getLength(int index) {
/*      */       return this.lengths[index];
/*      */     }
/*      */     
/*      */     public void close() {
/*      */       for (Source source : this.sources)
/*      */         Util.closeQuietly((Closeable)source); 
/*      */     }
/*      */   }
/*      */   
/*      */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000@\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\n\002\020\030\n\002\b\003\n\002\020\002\n\002\b\004\n\002\030\002\n\000\n\002\020\b\n\000\n\002\030\002\n\000\b\004\030\0002\0020\001B\023\b\000\022\n\020\002\032\0060\003R\0020\004¢\006\002\020\005J\006\020\016\032\0020\017J\006\020\020\032\0020\017J\r\020\021\032\0020\017H\000¢\006\002\b\022J\016\020\023\032\0020\0242\006\020\025\032\0020\026J\020\020\027\032\004\030\0010\0302\006\020\025\032\0020\026R\016\020\006\032\0020\007X\016¢\006\002\n\000R\030\020\002\032\0060\003R\0020\004X\004¢\006\b\n\000\032\004\b\b\020\tR\026\020\n\032\004\030\0010\013X\004¢\006\b\n\000\032\004\b\f\020\r¨\006\031"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Editor;", "", "entry", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "Lokhttp3/internal/cache/DiskLruCache;", "(Lokhttp3/internal/cache/DiskLruCache;Lokhttp3/internal/cache/DiskLruCache$Entry;)V", "done", "", "getEntry$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Entry;", "written", "", "getWritten$okhttp", "()[Z", "abort", "", "commit", "detach", "detach$okhttp", "newSink", "Lokio/Sink;", "index", "", "newSource", "Lokio/Source;", "okhttp"})
/*      */   public final class Editor {
/*      */     @Nullable
/*      */     private final boolean[] written;
/*      */     private boolean done;
/*      */     @NotNull
/*      */     private final DiskLruCache.Entry entry;
/*      */     
/*      */     @NotNull
/*      */     public final DiskLruCache.Entry getEntry$okhttp() {
/*      */       return this.entry;
/*      */     }
/*      */     
/*      */     public Editor(DiskLruCache.Entry entry) {
/*      */       this.entry = entry;
/*      */       this.written = this.entry.getReadable$okhttp() ? null : new boolean[DiskLruCache.this.getValueCount$okhttp()];
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     public final boolean[] getWritten$okhttp() {
/*      */       return this.written;
/*      */     }
/*      */     
/*      */     public final void detach$okhttp() {
/*      */       if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this))
/*      */         if (DiskLruCache.this.civilizedFileSystem) {
/*      */           DiskLruCache.this.completeEdit$okhttp(this, false);
/*      */         } else {
/*      */           this.entry.setZombie$okhttp(true);
/*      */         }  
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     public final Source newSource(int index) {
/*      */       DiskLruCache diskLruCache = DiskLruCache.this;
/*      */       boolean bool = false;
/*      */       synchronized (false) {
/*      */         Source source1;
/*      */         int $i$a$-synchronized-DiskLruCache$Editor$newSource$1 = 0;
/*      */         boolean bool1 = !this.done ? true : false, bool2 = false, bool3 = false;
/*      */         bool3 = false;
/*      */         boolean bool4 = false;
/*      */         if (!bool1) {
/*      */           boolean bool5 = false;
/*      */           String str = "Check failed.";
/*      */           throw (Throwable)new IllegalStateException(str.toString());
/*      */         } 
/*      */         if (!this.entry.getReadable$okhttp() || (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this) ^ true) != 0 || this.entry.getZombie$okhttp()) {
/*      */           Source source = null;
/*      */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache/DiskLruCache}, name=null} */
/*      */           return source;
/*      */         } 
/*      */         try {
/*      */           source1 = DiskLruCache.this.getFileSystem$okhttp().source(this.entry.getCleanFiles$okhttp().get(index));
/*      */         } catch (FileNotFoundException _) {
/*      */           source1 = null;
/*      */         } 
/*      */         Source source2 = source1;
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache/DiskLruCache}, name=null} */
/*      */         return source2;
/*      */       } 
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Sink newSink(int index) {
/*      */       DiskLruCache diskLruCache = DiskLruCache.this;
/*      */       boolean bool = false;
/*      */       synchronized (false) {
/*      */         int $i$a$-synchronized-DiskLruCache$Editor$newSink$1 = 0;
/*      */         boolean bool1 = !this.done ? true : false, bool2 = false, bool3 = false;
/*      */         bool3 = false;
/*      */         boolean bool4 = false;
/*      */         if (!bool1) {
/*      */           boolean bool5 = false;
/*      */           String str = "Check failed.";
/*      */           throw (Throwable)new IllegalStateException(str.toString());
/*      */         } 
/*      */         if ((Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this) ^ true) != 0) {
/*      */           Sink sink2 = Okio.blackhole();
/*      */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache/DiskLruCache}, name=null} */
/*      */           return sink2;
/*      */         } 
/*      */         if (!this.entry.getReadable$okhttp()) {
/*      */           Intrinsics.checkNotNull(this.written);
/*      */           this.written[index] = true;
/*      */         } 
/*      */         File dirtyFile = this.entry.getDirtyFiles$okhttp().get(index);
/*      */         Sink sink = null;
/*      */         try {
/*      */           sink = DiskLruCache.this.getFileSystem$okhttp().sink(dirtyFile);
/*      */         } catch (FileNotFoundException _) {
/*      */           Sink sink2 = Okio.blackhole();
/*      */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache/DiskLruCache}, name=null} */
/*      */           return sink2;
/*      */         } 
/*      */         Sink sink1 = (Sink)new FaultHidingSink(sink, new DiskLruCache$Editor$newSink$$inlined$synchronized$lambda$1(this, index));
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache/DiskLruCache}, name=null} */
/*      */         return sink1;
/*      */       } 
/*      */     }
/*      */     
/*      */     public final void commit() throws IOException {
/*      */       DiskLruCache diskLruCache = DiskLruCache.this;
/*      */       boolean bool = false;
/*      */       synchronized (false) {
/*      */         int $i$a$-synchronized-DiskLruCache$Editor$commit$1 = 0;
/*      */         boolean bool1 = !this.done ? true : false, bool2 = false, bool3 = false;
/*      */         bool3 = false;
/*      */         boolean bool4 = false;
/*      */         if (!bool1) {
/*      */           boolean bool5 = false;
/*      */           String str = "Check failed.";
/*      */           throw (Throwable)new IllegalStateException(str.toString());
/*      */         } 
/*      */         if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this))
/*      */           DiskLruCache.this.completeEdit$okhttp(this, true); 
/*      */         this.done = true;
/*      */         Unit unit = Unit.INSTANCE;
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache/DiskLruCache}, name=null} */
/*      */       } 
/*      */     }
/*      */     
/*      */     public final void abort() throws IOException {
/*      */       DiskLruCache diskLruCache = DiskLruCache.this;
/*      */       boolean bool = false;
/*      */       synchronized (false) {
/*      */         int $i$a$-synchronized-DiskLruCache$Editor$abort$1 = 0;
/*      */         boolean bool1 = !this.done ? true : false, bool2 = false, bool3 = false;
/*      */         bool3 = false;
/*      */         boolean bool4 = false;
/*      */         if (!bool1) {
/*      */           boolean bool5 = false;
/*      */           String str = "Check failed.";
/*      */           throw (Throwable)new IllegalStateException(str.toString());
/*      */         } 
/*      */         if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this))
/*      */           DiskLruCache.this.completeEdit$okhttp(this, false); 
/*      */         this.done = true;
/*      */         Unit unit = Unit.INSTANCE;
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache/DiskLruCache}, name=null} */
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000v\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\002\n\002\020!\n\002\030\002\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\t\n\002\020\026\n\002\b\003\n\002\020\b\n\002\b\005\n\002\020\013\n\002\b\005\n\002\020\t\n\002\b\b\n\002\020\001\n\000\n\002\020 \n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\b\004\030\0002\0020\001B\017\b\000\022\006\020\002\032\0020\003¢\006\002\020\004J\026\020.\032\0020/2\f\0200\032\b\022\004\022\0020\00301H\002J\020\0202\032\002032\006\0204\032\0020\032H\002J\033\0205\032\002062\f\0200\032\b\022\004\022\0020\00301H\000¢\006\002\b7J\023\0208\032\b\030\00109R\0020\fH\000¢\006\002\b:J\025\020;\032\002062\006\020<\032\0020=H\000¢\006\002\b>R\032\020\005\032\b\022\004\022\0020\0070\006X\004¢\006\b\n\000\032\004\b\b\020\tR \020\n\032\b\030\0010\013R\0020\fX\016¢\006\016\n\000\032\004\b\r\020\016\"\004\b\017\020\020R\032\020\021\032\b\022\004\022\0020\0070\006X\004¢\006\b\n\000\032\004\b\022\020\tR\024\020\002\032\0020\003X\004¢\006\b\n\000\032\004\b\023\020\024R\024\020\025\032\0020\026X\004¢\006\b\n\000\032\004\b\027\020\030R\032\020\031\032\0020\032X\016¢\006\016\n\000\032\004\b\033\020\034\"\004\b\035\020\036R\032\020\037\032\0020 X\016¢\006\016\n\000\032\004\b!\020\"\"\004\b#\020$R\032\020%\032\0020&X\016¢\006\016\n\000\032\004\b'\020(\"\004\b)\020*R\032\020+\032\0020 X\016¢\006\016\n\000\032\004\b,\020\"\"\004\b-\020$¨\006?"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Entry;", "", "key", "", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;)V", "cleanFiles", "", "Ljava/io/File;", "getCleanFiles$okhttp", "()Ljava/util/List;", "currentEditor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getCurrentEditor$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Editor;", "setCurrentEditor$okhttp", "(Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "dirtyFiles", "getDirtyFiles$okhttp", "getKey$okhttp", "()Ljava/lang/String;", "lengths", "", "getLengths$okhttp", "()[J", "lockingSourceCount", "", "getLockingSourceCount$okhttp", "()I", "setLockingSourceCount$okhttp", "(I)V", "readable", "", "getReadable$okhttp", "()Z", "setReadable$okhttp", "(Z)V", "sequenceNumber", "", "getSequenceNumber$okhttp", "()J", "setSequenceNumber$okhttp", "(J)V", "zombie", "getZombie$okhttp", "setZombie$okhttp", "invalidLengths", "", "strings", "", "newSource", "Lokio/Source;", "index", "setLengths", "", "setLengths$okhttp", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "snapshot$okhttp", "writeLengths", "writer", "Lokio/BufferedSink;", "writeLengths$okhttp", "okhttp"})
/*      */   public final class Entry {
/*      */     @NotNull
/*      */     private final long[] lengths;
/*      */     @NotNull
/*      */     private final List<File> cleanFiles;
/*      */     @NotNull
/*      */     private final List<File> dirtyFiles;
/*      */     private boolean readable;
/*      */     private boolean zombie;
/*      */     @Nullable
/*      */     private DiskLruCache.Editor currentEditor;
/*      */     private int lockingSourceCount;
/*      */     private long sequenceNumber;
/*      */     @NotNull
/*      */     private final String key;
/*      */     
/*      */     public Entry(String key) {
/*      */       this.key = key;
/*      */       this.lengths = new long[DiskLruCache.this.getValueCount$okhttp()];
/*      */       boolean bool = false;
/*      */       this.cleanFiles = new ArrayList<>();
/*      */       bool = false;
/*      */       this.dirtyFiles = new ArrayList<>();
/*      */       StringBuilder fileBuilder = (new StringBuilder(this.key)).append('.');
/*      */       int truncateTo = fileBuilder.length();
/*      */       byte b;
/*      */       int i;
/*      */       for (b = 0, i = DiskLruCache.this.getValueCount$okhttp(); b < i; b++) {
/*      */         fileBuilder.append(b);
/*      */         List<File> list = this.cleanFiles;
/*      */         File file = new File(DiskLruCache.this.getDirectory(), fileBuilder.toString());
/*      */         boolean bool1 = false;
/*      */         list.add(file);
/*      */         fileBuilder.append(".tmp");
/*      */         list = this.dirtyFiles;
/*      */         file = new File(DiskLruCache.this.getDirectory(), fileBuilder.toString());
/*      */         bool1 = false;
/*      */         list.add(file);
/*      */         fileBuilder.setLength(truncateTo);
/*      */       } 
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final String getKey$okhttp() {
/*      */       return this.key;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final long[] getLengths$okhttp() {
/*      */       return this.lengths;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final List<File> getCleanFiles$okhttp() {
/*      */       return this.cleanFiles;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final List<File> getDirtyFiles$okhttp() {
/*      */       return this.dirtyFiles;
/*      */     }
/*      */     
/*      */     public final boolean getReadable$okhttp() {
/*      */       return this.readable;
/*      */     }
/*      */     
/*      */     public final void setReadable$okhttp(boolean <set-?>) {
/*      */       this.readable = <set-?>;
/*      */     }
/*      */     
/*      */     public final boolean getZombie$okhttp() {
/*      */       return this.zombie;
/*      */     }
/*      */     
/*      */     public final void setZombie$okhttp(boolean <set-?>) {
/*      */       this.zombie = <set-?>;
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     public final DiskLruCache.Editor getCurrentEditor$okhttp() {
/*      */       return this.currentEditor;
/*      */     }
/*      */     
/*      */     public final void setCurrentEditor$okhttp(@Nullable DiskLruCache.Editor <set-?>) {
/*      */       this.currentEditor = <set-?>;
/*      */     }
/*      */     
/*      */     public final int getLockingSourceCount$okhttp() {
/*      */       return this.lockingSourceCount;
/*      */     }
/*      */     
/*      */     public final void setLockingSourceCount$okhttp(int <set-?>) {
/*      */       this.lockingSourceCount = <set-?>;
/*      */     }
/*      */     
/*      */     public final long getSequenceNumber$okhttp() {
/*      */       return this.sequenceNumber;
/*      */     }
/*      */     
/*      */     public final void setSequenceNumber$okhttp(long <set-?>) {
/*      */       this.sequenceNumber = <set-?>;
/*      */     }
/*      */     
/*      */     public final void setLengths$okhttp(@NotNull List<String> strings) throws IOException {
/*      */       Intrinsics.checkNotNullParameter(strings, "strings");
/*      */       if (strings.size() != DiskLruCache.this.getValueCount$okhttp())
/*      */         throw new KotlinNothingValueException(); 
/*      */       try {
/*      */         byte b;
/*      */         int i;
/*      */         for (b = 0, i = strings.size(); b < i; b++) {
/*      */           String str = strings.get(b);
/*      */           boolean bool = false;
/*      */           this.lengths[b] = Long.parseLong(str);
/*      */         } 
/*      */       } catch (NumberFormatException _) {
/*      */         NumberFormatException numberFormatException1;
/*      */         throw new KotlinNothingValueException();
/*      */       } 
/*      */     }
/*      */     
/*      */     public final void writeLengths$okhttp(@NotNull BufferedSink writer) throws IOException {
/*      */       Intrinsics.checkNotNullParameter(writer, "writer");
/*      */       for (long length : this.lengths)
/*      */         writer.writeByte(32).writeDecimalLong(length); 
/*      */     }
/*      */     
/*      */     private final Void invalidLengths(List strings) throws IOException {
/*      */       throw (Throwable)new IOException("unexpected journal line: " + strings);
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     public final DiskLruCache.Snapshot snapshot$okhttp() {
/*      */       Object $this$assertThreadHoldsLock$iv = DiskLruCache.this;
/*      */       int $i$f$assertThreadHoldsLock = 0;
/*      */       if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
/*      */         Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()");
/*      */         throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv);
/*      */       } 
/*      */       if (!this.readable)
/*      */         return null; 
/*      */       if (!DiskLruCache.this.civilizedFileSystem && (this.currentEditor != null || this.zombie))
/*      */         return null; 
/*      */       $i$f$assertThreadHoldsLock = 0;
/*      */       List<Source> sources = new ArrayList();
/*      */       long[] lengths = (long[])this.lengths.clone();
/*      */       try {
/*      */         byte b;
/*      */         int i;
/*      */         for (b = 0, i = DiskLruCache.this.getValueCount$okhttp(); b < i; b++) {
/*      */           List<Source> list = sources;
/*      */           Source source = newSource(b);
/*      */           boolean bool = false;
/*      */           list.add(source);
/*      */         } 
/*      */         return new DiskLruCache.Snapshot(this.key, this.sequenceNumber, sources, lengths);
/*      */       } catch (FileNotFoundException _) {
/*      */         FileNotFoundException fileNotFoundException1;
/*      */         for (Source source : sources)
/*      */           Util.closeQuietly((Closeable)source); 
/*      */         try {
/*      */           DiskLruCache.this.removeEntry$okhttp(this);
/*      */         } catch (IOException iOException) {}
/*      */         return null;
/*      */       } 
/*      */     }
/*      */     
/*      */     private final Source newSource(int index) {
/*      */       Source fileSource = DiskLruCache.this.getFileSystem$okhttp().source(this.cleanFiles.get(index));
/*      */       if (DiskLruCache.this.civilizedFileSystem)
/*      */         return fileSource; 
/*      */       int i;
/*      */       this.lockingSourceCount = (i = this.lockingSourceCount) + 1;
/*      */       return (Source)new DiskLruCache$Entry$newSource$1(fileSource, fileSource);
/*      */     }
/*      */     
/*      */     @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\027\n\000\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\002\n\000*\001\000\b\n\030\0002\0020\001J\b\020\004\032\0020\005H\026R\016\020\002\032\0020\003X\016¢\006\002\n\000¨\006\006"}, d2 = {"okhttp3/internal/cache/DiskLruCache$Entry$newSource$1", "Lokio/ForwardingSource;", "closed", "", "close", "", "okhttp"})
/*      */     public static final class DiskLruCache$Entry$newSource$1 extends ForwardingSource {
/*      */       private boolean closed;
/*      */       
/*      */       DiskLruCache$Entry$newSource$1(Source $captured_local_variable$1, Source $super_call_param$2) {
/*      */         super($super_call_param$2);
/*      */       }
/*      */       
/*      */       public void close() {
/*      */         super.close();
/*      */         if (!this.closed) {
/*      */           this.closed = true;
/*      */           DiskLruCache diskLruCache = DiskLruCache.this;
/*      */           boolean bool = false;
/*      */           synchronized (false) {
/*      */             int $i$a$-synchronized-DiskLruCache$Entry$newSource$1$close$1 = 0;
/*      */             int i;
/*      */             DiskLruCache.Entry.this.setLockingSourceCount$okhttp((i = DiskLruCache.Entry.this.getLockingSourceCount$okhttp()) + -1);
/*      */             if (DiskLruCache.Entry.this.getLockingSourceCount$okhttp() == 0 && DiskLruCache.Entry.this.getZombie$okhttp())
/*      */               DiskLruCache.this.removeEntry$okhttp(DiskLruCache.Entry.this); 
/*      */             Unit unit = Unit.INSTANCE;
/*      */             /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/cache/DiskLruCache}, name=null} */
/*      */           } 
/*      */         } 
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\"\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\000\n\002\020\016\n\002\b\005\n\002\030\002\n\002\b\005\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\020\020\003\032\0020\0048\006XD¢\006\002\n\000R\020\020\005\032\0020\0068\006XD¢\006\002\n\000R\020\020\007\032\0020\0068\006XD¢\006\002\n\000R\020\020\b\032\0020\0068\006XD¢\006\002\n\000R\020\020\t\032\0020\0068\006XD¢\006\002\n\000R\020\020\n\032\0020\0068\006XD¢\006\002\n\000R\020\020\013\032\0020\f8\006X\004¢\006\002\n\000R\020\020\r\032\0020\0068\006XD¢\006\002\n\000R\020\020\016\032\0020\0068\006XD¢\006\002\n\000R\020\020\017\032\0020\0068\006XD¢\006\002\n\000R\020\020\020\032\0020\0068\006XD¢\006\002\n\000¨\006\021"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Companion;", "", "()V", "ANY_SEQUENCE_NUMBER", "", "CLEAN", "", "DIRTY", "JOURNAL_FILE", "JOURNAL_FILE_BACKUP", "JOURNAL_FILE_TEMP", "LEGAL_KEY_PATTERN", "Lkotlin/text/Regex;", "MAGIC", "READ", "REMOVE", "VERSION_1", "okhttp"})
/*      */   public static final class Companion {
/*      */     private Companion() {}
/*      */   }
/*      */   
/*      */   public static final Companion Companion = new Companion(null);
/*      */   
/*      */   static {
/*      */     JOURNAL_FILE = "journal";
/*      */     JOURNAL_FILE_TEMP = "journal.tmp";
/*      */     JOURNAL_FILE_BACKUP = "journal.bkp";
/*      */     MAGIC = "libcore.io.DiskLruCache";
/*      */     VERSION_1 = "1";
/*      */     ANY_SEQUENCE_NUMBER = -1L;
/*      */     String str = "[a-z0-9_-]{1,120}";
/*      */     boolean bool = false;
/*      */     LEGAL_KEY_PATTERN = new Regex(str);
/*      */     CLEAN = "CLEAN";
/*      */     DIRTY = "DIRTY";
/*      */     REMOVE = "REMOVE";
/*      */     READ = "READ";
/*      */   }
/*      */   
/*      */   @JvmOverloads
/*      */   @Nullable
/*      */   public final Editor edit(@NotNull String key) throws IOException {
/*      */     return edit$default(this, key, 0L, 2, null);
/*      */   } }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/cache/DiskLruCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
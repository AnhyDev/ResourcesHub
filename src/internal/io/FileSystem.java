/*     */ package okhttp3.internal.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.JvmField;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okio.Okio;
/*     */ import okio.Sink;
/*     */ import okio.Source;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0004\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\003\n\002\020\013\n\002\b\005\n\002\020\t\n\000\n\002\030\002\n\002\b\002\bf\030\000 \0242\0020\001:\001\024J\020\020\002\032\0020\0032\006\020\004\032\0020\005H&J\020\020\006\032\0020\0072\006\020\004\032\0020\005H&J\020\020\b\032\0020\0072\006\020\t\032\0020\005H&J\020\020\n\032\0020\0132\006\020\004\032\0020\005H&J\030\020\f\032\0020\0072\006\020\r\032\0020\0052\006\020\016\032\0020\005H&J\020\020\017\032\0020\0032\006\020\004\032\0020\005H&J\020\020\020\032\0020\0212\006\020\004\032\0020\005H&J\020\020\022\032\0020\0232\006\020\004\032\0020\005H&¨\006\025"}, d2 = {"Lokhttp3/internal/io/FileSystem;", "", "appendingSink", "Lokio/Sink;", "file", "Ljava/io/File;", "delete", "", "deleteContents", "directory", "exists", "", "rename", "from", "to", "sink", "size", "", "source", "Lokio/Source;", "Companion", "okhttp"})
/*     */ public interface FileSystem
/*     */ {
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final FileSystem SYSTEM;
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\b\003\030\0002\0020\001:\001\005B\007\b\002¢\006\002\020\002R\026\020\003\032\0020\0048\006X\004ø\001\000¢\006\002\n\000¨\006\001\002\007\n\005\bF0\001¨\006\006"}, d2 = {"Lokhttp3/internal/io/FileSystem$Companion;", "", "()V", "SYSTEM", "Lokhttp3/internal/io/FileSystem;", "SystemFileSystem", "okhttp"})
/*     */   public static final class Companion
/*     */   {
/*     */     private Companion() {}
/*     */     
/*     */     @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000:\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\003\n\002\020\013\n\002\b\005\n\002\020\t\n\000\n\002\030\002\n\000\n\002\020\016\n\000\b\002\030\0002\0020\001B\005¢\006\002\020\002J\020\020\003\032\0020\0042\006\020\005\032\0020\006H\026J\020\020\007\032\0020\b2\006\020\005\032\0020\006H\026J\020\020\t\032\0020\b2\006\020\n\032\0020\006H\026J\020\020\013\032\0020\f2\006\020\005\032\0020\006H\026J\030\020\r\032\0020\b2\006\020\016\032\0020\0062\006\020\017\032\0020\006H\026J\020\020\020\032\0020\0042\006\020\005\032\0020\006H\026J\020\020\021\032\0020\0222\006\020\005\032\0020\006H\026J\020\020\023\032\0020\0242\006\020\005\032\0020\006H\026J\b\020\025\032\0020\026H\026¨\006\027"}, d2 = {"Lokhttp3/internal/io/FileSystem$Companion$SystemFileSystem;", "Lokhttp3/internal/io/FileSystem;", "()V", "appendingSink", "Lokio/Sink;", "file", "Ljava/io/File;", "delete", "", "deleteContents", "directory", "exists", "", "rename", "from", "to", "sink", "size", "", "source", "Lokio/Source;", "toString", "", "okhttp"})
/*     */     private static final class SystemFileSystem
/*     */       implements FileSystem
/*     */     {
/*     */       @NotNull
/*     */       public Source source(@NotNull File file) throws FileNotFoundException {
/*  50 */         Intrinsics.checkNotNullParameter(file, "file"); return Okio.source(file);
/*     */       } @NotNull
/*     */       public Sink sink(@NotNull File file) throws FileNotFoundException {
/*     */         Sink sink;
/*  54 */         Intrinsics.checkNotNullParameter(file, "file"); try {
/*  55 */           sink = Okio.sink$default(file, false, 1, null);
/*  56 */         } catch (FileNotFoundException _) {
/*     */           
/*  58 */           file.getParentFile().mkdirs();
/*  59 */           sink = Okio.sink$default(file, false, 1, null);
/*     */         } 
/*     */         return sink;
/*     */       } @NotNull
/*     */       public Sink appendingSink(@NotNull File file) throws FileNotFoundException {
/*     */         Sink sink;
/*  65 */         Intrinsics.checkNotNullParameter(file, "file"); try {
/*  66 */           sink = Okio.appendingSink(file);
/*  67 */         } catch (FileNotFoundException _) {
/*     */           
/*  69 */           file.getParentFile().mkdirs();
/*  70 */           sink = Okio.appendingSink(file);
/*     */         } 
/*     */         return sink;
/*     */       }
/*     */ 
/*     */       
/*     */       public void delete(@NotNull File file) throws IOException {
/*  77 */         Intrinsics.checkNotNullParameter(file, "file"); if (!file.delete() && file.exists())
/*  78 */           throw (Throwable)new IOException("failed to delete " + file); 
/*     */       }
/*     */       
/*     */       public boolean exists(@NotNull File file) {
/*  82 */         Intrinsics.checkNotNullParameter(file, "file"); return file.exists();
/*     */       } public long size(@NotNull File file) {
/*  84 */         Intrinsics.checkNotNullParameter(file, "file"); return file.length();
/*     */       }
/*     */       
/*     */       public void rename(@NotNull File from, @NotNull File to) throws IOException {
/*  88 */         Intrinsics.checkNotNullParameter(from, "from"); Intrinsics.checkNotNullParameter(to, "to"); delete(to);
/*  89 */         if (!from.renameTo(to)) {
/*  90 */           throw (Throwable)new IOException("failed to rename " + from + " to " + to);
/*     */         }
/*     */       }
/*     */ 
/*     */       
/*     */       public void deleteContents(@NotNull File directory) throws IOException {
/*  96 */         Intrinsics.checkNotNullParameter(directory, "directory"); if (directory.listFiles() != null) { File[] files = directory.listFiles();
/*  97 */           for (File file : files) {
/*  98 */             Intrinsics.checkNotNullExpressionValue(file, "file"); if (file.isDirectory()) {
/*  99 */               deleteContents(file);
/*     */             }
/* 101 */             if (!file.delete())
/* 102 */               throw (Throwable)new IOException("failed to delete " + file); 
/*     */           } 
/*     */           return; }
/*     */         
/*     */         directory.listFiles();
/* 107 */         throw (Throwable)new IOException("not a readable directory: " + directory); } @NotNull public String toString() { return "FileSystem.SYSTEM"; }
/*     */     
/*     */     }
/*     */   }
/*     */   
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   @NotNull
/*     */   Source source(@NotNull File paramFile) throws FileNotFoundException;
/*     */   
/*     */   @NotNull
/*     */   Sink sink(@NotNull File paramFile) throws FileNotFoundException;
/*     */   
/*     */   @NotNull
/*     */   Sink appendingSink(@NotNull File paramFile) throws FileNotFoundException;
/*     */   
/*     */   void delete(@NotNull File paramFile) throws IOException;
/*     */   
/*     */   boolean exists(@NotNull File paramFile);
/*     */   
/*     */   long size(@NotNull File paramFile);
/*     */   
/*     */   void rename(@NotNull File paramFile1, @NotNull File paramFile2) throws IOException;
/*     */   
/*     */   void deleteContents(@NotNull File paramFile) throws IOException;
/*     */   
/*     */   static {
/*     */     SYSTEM = new Companion.SystemFileSystem();
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/io/FileSystem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
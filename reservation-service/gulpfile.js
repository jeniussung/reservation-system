var gulp = require('gulp');
// var webserver = require('gulp-webserver');
// var concat = require('gulp-concat');
// var uglify = require('gulp-uglify');
// var minifyhtml = require('gulp-minify-html');
// var sass = require('gulp-sass');
// var livereload = require('gulp-livereload');
// var stripDebug = require('gulp-strip-debug');
var eslint = require('gulp-eslint');
var gulpIf = require('gulp-if');

var src = 'public/src';
var dist = 'public/dist';


var paths = {
   js: src + '/js/*.js',
   scss: src + '/scss/*.css',
   html: src + '/**/*.html'
};

function isFixed(file){
   return file.eslint != null && file.eslint.fixed;
}

gulp.task('server', function(){
   return gulp.src(dist + '/')
      .pipe(webserver());
});

gulp.task('combine-js', function () {
   return gulp.src(paths.js)
      .pipe(stripDebug())
      .pipe(uglify())
      .pipe(concat('script.js'))
      .pipe(gulp.dest(dist + '/js'));
});

// sass 파일을 css 로 컴파일한다.
gulp.task('compile-sass', function () {
   return gulp.src(paths.scss)
      .pipe(sass())
      .pipe(gulp.dest(dist + '/css'));
});

// HTML 파일을 압축한다.
gulp.task('compress-html', function () {
   return gulp.src(paths.html)
      .pipe(minifyhtml())
      .pipe(gulp.dest(dist + '/'));
});

// 파일 변경 감지 및 브라우저 재시작
gulp.task('watch', function () {
   livereload.listen();
   gulp.watch(paths.js, ['combine-js']);
   gulp.watch(paths.scss, ['compile-sass']);
   gulp.watch(paths.html, ['compress-html']);
   gulp.watch(dist + '/**').on('change', livereload.changed);
});

gulp.task('lint', function() {

   return gulp.src('public/src/js/details.js')
   // return gulp.src(paths.js)
      .pipe(eslint({
         fix: true,
         rules: {
            'camelcase': 1,
            'strict':2
         },
         globals: [
            'jQuery',
            '$'
         ],
         envs: [
            'browser'
         ]
      }))
      .pipe(eslint.format())
      .pipe(eslint.result(result => {
           console.log(`ESLint result: ${result.filePath}`);
           console.log(`# Messages: ${result.messages.length}`);
           console.log(`# Warnings: ${result.warningCount}`);
           console.log(`# Errors: ${result.errorCount}`);
      }))
    //   .pipe(gulpIf(isFixed, gulp.dest('./')))
    //   .pipe(gulp.dest('public/dist'));

});

//기본 task 설정
gulp.task('default', [
   'server', 'combine-js',
   'compile-sass', 'compress-html',
   'watch' ]);

EXE_NAME="app"
EXE_PATH="bin/$EXE_NAME"


build() {
  gcc -Wall -std=c99 ./src/*.c -o ${EXE_PATH}
}


debug() {
  gcc -g -Wall -std=c99 ./src/*.c -o ${EXE_PATH}
}


run() {
  ./$EXE_PATH $@
}


clean() {
  rm $EXE_PATH
}

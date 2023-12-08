echo "==> Cleaning project"
./gradlew clean
echo "==> Compiling project"
./gradlew --profile --rerun-tasks compileDebugSources
echo "==> Compilation complete"
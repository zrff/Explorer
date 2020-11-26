assets_path='../backend/src/main/webapp/assets'
if [ ! -d "$assets_path" ]; then
    echo "copy assets"
    cp -rf assets $assets_path
fi

assets_path='../backend/src/main/webapp/png'
if [ ! -d "$assets_path" ]; then
    echo "copy png"
    cp -rf png $assets_path
fi

assets_path='../backend/src/main/webapp/dist/'
if [ -d "$assets_path" ]; then
    echo "copy dist"
    cp -rf dist/* "../backend/src/main/webapp/dist/"
fi
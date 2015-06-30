package com.github.finder;

public class Main{
    public Main(String[] arguments){
    }

    public static void main(String[] args){
        new Main(args);
    }

 	private Args parseArguments(String[] arguments){
        Args args = new Args();
        try {
            CmdLineParser parser = new CmdLineParser(args);
            parser.parseArgument(arguments);
        } catch (CmdLineException e) {
        }
        return args;
    }
<<<<<<< HEAD

     private boolean isTarget(File file){
=======
    private boolean isTarget(File file){
>>>>>>> issue/7
        boolean flag = true;
        if(args.getName() != null){
            flag &= checkTargetName(file, args.getName());
        }
<<<<<<< HEAD
        return flag;
    }
    private boolean checkTargetName(File file, String pattern){
        String name = file.getName();
        return name.indexOf(pattern) >= 0;
=======
        if(args.getType() != null){
            flag &= checkTargetType(file, args.getType());
        }
        return flag;
    }
    private boolean checkTargetType(File file, String type){
        type = type.toLowerCase();
        if(type.equals("d") || type.equals("directory")){
            return file.isDirectory();
        }
        else if(type.equals("f") || type.equals("file")){
            return file.isFile();
        }
        else if(type.equals("h") || type.equals("hidden")){
            return file.isHidden();
        }
        return false;
>>>>>>> issue/7
    }
}
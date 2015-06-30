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

    private boolean isTarget(File file){
        boolean flag = true;
        if(args.getName() != null){
            flag &= checkTargetName(file, args.getName());
        }
        if(args.getType() != null){
            flag &= checkTargetType(file, args.getType());
        }
        if(args.getSize() != null){
            flag &= checkTargetSize(file, args.getSize());
        }
        if(args.getGrep() != null){
            flag &= checkGrep(file, args.getGrep());
        }

        return flag;
    }
    private boolean checkTargetName(File file, String pattern){
        String name = file.getName();
        return name.indexOf(pattern) >= 0;
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
    }
    
    private boolean checkTargetSize(File file, String sizeString){
        if(file.isFile()){
            char sign = sizeString.charAt(0);
            String string = sizeString.substring(1);
            int size = Integer.parseInt(string);

            switch(sign){
            case '>':
                return file.length() > size;
            case '<':
                return file.length() < size;
            case '=':
                return file.length() == size;
            default:
                // ignore
            }
        }
        return false;
    }
    private boolean checkGrep(File file, String pattern){
        if(file.isFile()){
            try(BufferedReader in = new BufferedReader(new FileReader(file))){
                String line;
                while((line = in.readLine()) != null){
                    if(line.indexOf(pattern) >= 0){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public Main(String[] arguments){
        Args args = parseArguments(arguments);

        Finder finder = new Finder(args);
        for(String base: args){
            String[] items = finder.find(base);
            for(String item: items){
                System.out.println(item);
            }
        }
    }
}
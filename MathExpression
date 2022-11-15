public class Solution {
    List<String> rst;
    public List<String> addOperators(String num, int target) {
        rst = new ArrayList<String>();
        if(num == null || num.length() == 0) return rst;
        helper("", num, target, 0, 0, 0);
        return rst;
    }
    public void helper(String path, String num, int target, int pos, long eval, long multed){
        if(pos == num.length()){
            if(target == eval)
                rst.add(path);
            return;
        }
        for(int i = pos; i < num.length(); i++){
           // 0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
            if(i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if(pos == 0){
                helper(path + cur, num, target, i + 1, cur, cur);
            }
            else{
                helper(path + "+" + cur, num, target, i + 1, eval + cur , cur);
                
                helper(path + "-" + cur, num, target, i + 1, eval - cur, -1 * cur);
                //注意乘法
                helper(path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );
                if (path.contains("+") || path.contains("-")) {
                    helper("(" + path + ")" + "*" + cur, num, target, i + 1, eval * cur, eval * cur );
                }
            }
        }
    }
}

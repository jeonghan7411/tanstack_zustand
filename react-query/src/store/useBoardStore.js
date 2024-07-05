import { create } from "zustand";
import { devtools } from "zustand/middleware";


let useBoardStore = (set) => ({
  selectBoardInfo : {},
  setSelectBoardInfo : (board) => set({ selectBoardInfo : board}),
});


useBoardStore = devtools(useBoardStore); // 디버깅 가능하게
// useBoardStore = persist(useBoardStore, { name: 'useBoardStore' }); // 로컬에저장하는거


export default create(useBoardStore);



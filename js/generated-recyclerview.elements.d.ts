/* generated @ 2018-11-05T13:37:18.622 */
import "./base.elements";
import "./generated-recyclerview.attributes";
declare module "LayoutTypes" {
  interface NormalizedRecyclerViewElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<RecyclerViewAttributes, LayoutAttributes> {
    type: "recyclerView";
  }
  interface DenormalizedRecyclerViewElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<RecyclerViewAttributes, LayoutAttributes, ChildrenViewAttributes, RecyclerViewLayoutParamsAttributes> {
    type: "recyclerView";
  }}

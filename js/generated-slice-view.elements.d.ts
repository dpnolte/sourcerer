/* generated @ 2018-11-05T13:37:48.522 */
import "./base.elements";
import "./generated-slice-view.attributes";
, import "./generated.attributes";
declare module "LayoutTypes" {
  interface NormalizedSliceViewElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<SliceViewAttributes, LayoutAttributes> {
    type: "sliceView";
  }
  interface DenormalizedSliceViewElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<SliceViewAttributes, LayoutAttributes, ChildrenViewAttributes, ViewGroupLayoutParamsAttributes> {
    type: "sliceView";
  }}

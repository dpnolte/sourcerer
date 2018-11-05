/* generated @ 2018-11-05T13:38:34.260 */
import "./base.elements";
import "./generated-gridlayout.attributes";
declare module "LayoutTypes" {
  interface NormalizedGridLayoutElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<GridLayoutAttributes, LayoutAttributes> {
    type: "gridlayout.gridLayout";
  }
  interface DenormalizedGridLayoutElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<GridLayoutAttributes, LayoutAttributes, ChildrenViewAttributes, GridLayoutLayoutParamsAttributes> {
    type: "gridlayout.gridLayout";
  }}
